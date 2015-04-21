package com.excilys.cdb.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.cdb.exception.PropertiesNotFound;

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

	private ConnectionFactory() {

		prop = new Properties();
		InputStream input = null;

		try {
			input = getClass().getClassLoader().getResourceAsStream(
					"config.properties");
			if (input != null) {
				prop.load(input);
			}
		} catch (IOException ex) {
			throw new PropertiesNotFound();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				throw new PropertiesNotFound();
			}
		}

		try {
			Class.forName(prop.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			System.err.println("Driver not found!");
			throw new RuntimeException();
		}

	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(prop.getProperty("url"),
					prop.getProperty("user"), prop.getProperty("password"));

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
			System.err.println("Error : Close Connection");
			throw new RuntimeException();
		}
	}
}
