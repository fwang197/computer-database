package com.excilys.cdb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.model.Company;

public class CompanyMapper {
	/**
	 * Convert a line of the resultSet to an object Company.
	 *
	 * @param rs
	 * @return the company
	 */
	public static Company toCompany(ResultSet rs) throws SQLException {
		Company comp = null;
		if (rs.next()) {
			comp = new Company();
			comp.setId(rs.getLong("id"));
			comp.setName(rs.getString("name"));
		}
		return comp;
	}
}
