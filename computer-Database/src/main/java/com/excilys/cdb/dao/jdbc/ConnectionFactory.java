package com.excilys.cdb.dao.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.cdb.exception.BoneCPException;
import com.excilys.cdb.exception.PropertiesNotFound;
import com.excilys.cdb.exception.ServiceException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Connection objects.
 */
public enum ConnectionFactory {

	/** The instance. */
	INSTANCE;

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */

	private Properties prop;
	private BoneCP pool;
	public static final ThreadLocal<Connection> threadConnection = new ThreadLocal<Connection>();

	private ConnectionFactory() {

		prop = new Properties();
		InputStream input = null;

		try {
			input = getClass().getClassLoader().getResourceAsStream(
					"config.properties");
			prop.load(input);

			Class.forName(prop.getProperty("driver"));

			BoneCPConfig conf = new BoneCPConfig();
			conf.setJdbcUrl(prop.getProperty("url"));
			conf.setUsername(prop.getProperty("user"));
			conf.setPassword(prop.getProperty("password"));
			conf.setMinConnectionsPerPartition(1);
			conf.setMaxConnectionsPerPartition(5);
			conf.setPartitionCount(2);
			pool = new BoneCP(conf);
		} catch (IOException ex) {
			throw new PropertiesNotFound();
		} catch (SQLException e) {
			throw new BoneCPException();
		} catch (ClassNotFoundException e) {
			System.err.println("Driver not found!");
			throw new RuntimeException();
		}

	}

	public Connection getConnection() {
		try {
			if (threadConnection.get() == null) {
				threadConnection.set(pool.getConnection());
			}
			return threadConnection.get();

		} catch (SQLException e) {
			System.err.println("Error : Connection!");
			throw new RuntimeException();
		}
	}

	public void closeConnection() {

		try {
			Connection conn = threadConnection.get();
			if (conn != null)
				conn.close();
			threadConnection.remove();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error : Close Connection");
			throw new RuntimeException();
		}
	}

	public void startTransaction() throws SQLException {
		Connection conn = threadConnection.get();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
	}

	public void commit() throws SQLException {

		Connection conn = threadConnection.get();
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
	}

	public void rollback() {

		Connection conn = threadConnection.get();
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
}
