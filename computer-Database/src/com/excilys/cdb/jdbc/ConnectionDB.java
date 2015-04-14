package com.excilys.cdb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * Le Singleton ConnectionDB qui permet de se connecter une seule fois à la base
 * de données.
 * 
 * @author excilys
 *
 */
public class ConnectionDB {

	/** The instance. */
	private static ConnectionDB instance = new ConnectionDB();
	// Son URL local
	/** The url. */
	private String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	// Son nom user
	/** The user. */
	private String user = "admincdb";
	// Son mot de passe
	/** The passwd. */
	private String passwd = "qwerty1234";
	// Sa connection
	/** The connection. */
	private Connection connection;

	/**
	 * Instantiates a new connection db.
	 */
	private ConnectionDB() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}

		try {
			connection = DriverManager.getConnection(url, user, passwd);

		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		//
		// if (connection != null) {
		// System.out.println("OK!");
		// } else {
		// System.out.println("Failed!");
		// }
	}

	/**
	 * Gets the single instance of ConnectionDB.
	 *
	 * @return single instance of ConnectionDB
	 */
	public static Connection getInstance() {
		return instance.connection;
	}
}
