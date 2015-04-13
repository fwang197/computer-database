package com.excilys.cdb.ui;

import java.sql.SQLException;

import com.excilys.cdb.jdbc.ConnectionDB;

/**
 * L'action qui permet de quitter l'application.
 * 
 * @author excilys
 *
 */
public class LeaveAppAction extends Action {

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
		Menu.loop = false;
	}

}
