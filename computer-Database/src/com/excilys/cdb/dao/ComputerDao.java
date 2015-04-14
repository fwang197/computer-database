package com.excilys.cdb.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.excilys.cdb.jdbc.ConnectionDB;
import com.excilys.cdb.model.Computer;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerDao.
 */
public class ComputerDao implements Dao<Computer> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.dao.Dao#create(java.lang.Object)
	 */
	@Override
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.dao.Dao#find(long)
	 */
	@Override
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.dao.Dao#update(java.lang.Object)
	 */
	@Override
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.dao.Dao#delete(java.lang.Object)
	 */
	@Override
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.dao.Dao#findAll()
	 */
	@Override
	public List<Computer> findAll() {
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
