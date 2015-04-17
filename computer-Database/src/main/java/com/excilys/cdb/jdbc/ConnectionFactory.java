package main.java.com.excilys.cdb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Connection objects.
 */
public enum ConnectionFactory {

	/** The instance. */
	INSTANCE;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Driver not found!");
			throw new RuntimeException();
		}
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
		String user = "admincdb";
		String passwd = "qwerty1234";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, passwd);

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
