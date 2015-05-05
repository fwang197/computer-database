package com.excilys.cdb.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exception.ServiceException;

/**
 * A factory for creating Connection objects.
 */
public class ConnectionFactory {

	private final Logger logger = LoggerFactory
			.getLogger(ConnectionFactory.class);

	private DataSource datasource = null;

	/** The Constant threadConnection. */
	public static final ThreadLocal<Connection> threadConnection = new ThreadLocal<Connection>();

	/**
	 * Instantiates a new connection factory.
	 */
	private ConnectionFactory() {
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		try {
			if (threadConnection.get() == null) {
				threadConnection.set(datasource.getConnection());
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

	public DataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}
}
