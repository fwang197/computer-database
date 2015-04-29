package com.excilys.cdb.dao.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private final Logger logger = LoggerFactory
			.getLogger(ConnectionFactory.class);
	/** The prop. */
	private Properties prop;

	/** The pool of connections. */
	private BoneCP pool;

	/** The Constant threadConnection. */
	public static final ThreadLocal<Connection> threadConnection = new ThreadLocal<Connection>();

	/**
	 * Instantiates a new connection factory.
	 */
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
			logger.error("Connection Factory : Input error");
			throw new PropertiesNotFound();
		} catch (SQLException e) {
			logger.error("Connection Factory : BoneCP error");
			throw new BoneCPException();
		} catch (ClassNotFoundException e) {
			logger.error("Connection Factory : Driver not found");
			throw new RuntimeException();
		}

	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		try {
			if (threadConnection.get() == null) {
				threadConnection.set(pool.getConnection());
			}
			return threadConnection.get();

		} catch (SQLException e) {
			logger.error("Connection Factory : get Connection error");
			throw new RuntimeException();
		}
	}

	/**
	 * Close connection.
	 */
	public void closeConnection() {

		try {
			Connection conn = threadConnection.get();
			if (conn != null)
				conn.close();
			threadConnection.remove();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Connection Factory : close Connection error");
			throw new RuntimeException();
		}
	}

	/**
	 * Start transaction.
	 *
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void startTransaction() throws SQLException {
		Connection conn = threadConnection.get();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			logger.error("Connection Factory : start Transaction error");
			throw new SQLException();
		}
	}

	/**
	 * Commit.
	 *
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void commit() throws SQLException {

		Connection conn = threadConnection.get();
		try {
			conn.commit();
		} catch (SQLException e) {
			logger.error("Connection Factory : commit error");
			throw new SQLException();
		}
	}

	/**
	 * Rollback.
	 */
	public void rollback() {

		Connection conn = threadConnection.get();
		try {
			conn.rollback();
		} catch (SQLException e) {
			logger.error("Connection Factory : rollback error");
			throw new ServiceException();
		}
	}
}
