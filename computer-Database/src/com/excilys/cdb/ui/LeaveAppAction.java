package com.excilys.cdb.ui;

import java.sql.SQLException;

import com.excilys.cdb.jdbc.ConnectionDB;

// TODO: Auto-generated Javadoc
/**
 * L'action qui permet de quitter l'application.
 * 
 * @author excilys
 *
 */
public class LeaveAppAction extends Action {

	/**
	 * Instantiates a new leave app action.
	 *
	 * @param description
	 *            the description
	 */
	public LeaveAppAction(String description) {
		this.description = description;
	}

	/**
	 * On ferme le scanner et la connexion à la base de donnée. On sort de la
	 * boucle.
	 */
	@Override
	public void execute() {
		try {
			ConnectionDB.getInstance().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Menu.sc.close();
		System.out.println("Bye!");
		System.exit(0);
	}

}
