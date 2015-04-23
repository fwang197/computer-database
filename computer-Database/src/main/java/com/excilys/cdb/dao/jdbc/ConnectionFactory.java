package com.excilys.cdb.dao.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.cdb.exception.BoneCPException;
import com.excilys.cdb.exception.PropertiesNotFound;
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
		Connection connection = null;
		try {
			connection = pool.getConnection();

		} catch (SQLException e) {
			System.err.println("Error : Connection!");
			throw new RuntimeException();
		}
		return connection;
	}

	/**
	 * Close connection.
	 *
	 * @param conn
	 *            the conn
	 */
	public void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error : Close Connection");
			throw new RuntimeException();
		}
	}
}
