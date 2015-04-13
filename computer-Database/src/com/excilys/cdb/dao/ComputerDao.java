package com.excilys.cdb.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.excilys.cdb.jdbc.ConnectionDB;
import com.excilys.cdb.model.Computer;

public class ComputerDao extends Dao<Computer> {

	public void create(Computer comp) {
		try {
			PreparedStatement prepare = ConnectionDB
					.getInstance()
					.prepareStatement(
							"insert into computer(name,introduced,discontinued,company_id) values (?,?,?,?)");
			prepare.setString(1, comp.getName());
			prepare.setTimestamp(2, comp.getIntroduced());
			prepare.setTimestamp(3, comp.getDiscontinued());
			prepare.setLong(4, comp.getCompany().getId());
			prepare.executeUpdate();
			prepare.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Computer find(long id) {
		Computer comp = null;
		try {
			ResultSet result = ConnectionDB.getInstance().createStatement()
					.executeQuery("select * from computer where id = " + id);
			if (result.next()) {
				comp = new Computer(id, result.getString("name"),
						result.getTimestamp("introduced"),
						result.getTimestamp("discontinued"),
						new CompanyDao().find(result.getLong("company_id")));
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comp;
	}

	public void update(Computer comp) {
		try {

			ConnectionDB
					.getInstance()
					.createStatement()
					.executeUpdate(
							"update computer set name = '" + comp.getName()
									+ "' , introduced = "
									+ comp.getIntroduced()
									+ " , discontinued = "
									+ comp.getDiscontinued()
									+ " , company_id = "
									+ comp.getCompany().getId()
									+ " where id = " + comp.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Computer comp) {
		try {
			System.out.println(comp.toString());
			ConnectionDB
					.getInstance()
					.createStatement()
					.executeUpdate(
							"delete from computer where id = " + comp.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public LinkedList<Computer> findAll() {
		LinkedList<Computer> lcomputer = new LinkedList<Computer>();
		try {
			ResultSet result = ConnectionDB.getInstance().createStatement()
					.executeQuery("select * from computer");
			while (result.next()) {
				lcomputer.add(new Computer(result.getLong("id"), result
						.getString("name"), result.getTimestamp("introduced"),
						result.getTimestamp("discontinued"), new CompanyDao()
								.find(result.getLong("company_id"))));
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lcomputer;
	}
}
