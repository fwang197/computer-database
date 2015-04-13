package com.excilys.cdb.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.excilys.cdb.jdbc.ConnectionDB;
import com.excilys.cdb.model.Company;

public class CompanyDao extends Dao<Company> {

	@Override
	public void create(Company comp) {
		try {
			PreparedStatement prepare = ConnectionDB.getInstance()
					.prepareStatement("insert into company(name) values (?)");
			prepare.setString(1, comp.getName());
			prepare.executeUpdate();
			prepare.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Company find(long id) {
		Company comp = null;
		try {
			ResultSet result = ConnectionDB.getInstance().createStatement()
					.executeQuery("select * from company where id = " + id);
			if (result.next()) {
				comp = new Company(id, result.getString("name"));
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comp;
	}

	@Override
	public void update(Company comp) {
		try {

			ConnectionDB
					.getInstance()
					.createStatement()
					.executeUpdate(
							"update company set name = '" + comp.getName()
									+ "' where id = " + comp.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Company comp) {
		try {

			ConnectionDB
					.getInstance()
					.createStatement()
					.executeUpdate(
							"delete from company where id = " + comp.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public LinkedList<Company> findAll() {
		LinkedList<Company> lcompany = new LinkedList<Company>();
		try {
			ResultSet result = ConnectionDB.getInstance().createStatement()
					.executeQuery("select * from company");
			while (result.next()) {
				lcompany.add(new Company(result.getLong("id"), result
						.getString("name")));
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lcompany;
	}
}
