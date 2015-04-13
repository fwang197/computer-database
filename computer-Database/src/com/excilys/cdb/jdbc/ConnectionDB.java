package com.excilys.cdb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Le Singleton ConnectionDB qui permet de se connecter une seule fois à la base
 * de données.
 * 
 * @author excilys
 *
 */
public class ConnectionDB {

	private static ConnectionDB instance = new ConnectionDB();
	// Son URL local
	private String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	// Son nom user
	private String user = "admincdb";
	// Son mot de passe
	private String passwd = "qwerty1234";
	// Sa connection
	private Connection connection;

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

	public static Connection getInstance() {
		return instance.connection;
	}

	public void close() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
