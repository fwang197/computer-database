package com.excilys.cdb.ui;

import java.sql.SQLException;

import com.excilys.cdb.jdbc.ConnectionDB;

public class LeaveAppAction extends Action {

	public LeaveAppAction(String description) {
		this.description = description;
	}

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
