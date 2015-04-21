package com.excilys.cdb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class ComputerMapper {

	/**
	 * To computer.
	 *
	 * @param rs
	 *            the rs
	 * @return the computer
	 */
	public static Computer toComputer(ResultSet rs) throws SQLException {
		Computer comp = null;
		if (rs.next()) {
			comp = new Computer();
			comp.setId(rs.getLong("c_id"));
			comp.setIntroduced(DateMapper.toLocalDateTime(rs
					.getTimestamp("introduced")));

			comp.setDiscontinued(DateMapper.toLocalDateTime(rs
					.getTimestamp("discontinued")));

			comp.setName(rs.getString("c_name"));
			if (rs.getLong("company_id") != 0) {
				comp.setCompany(new Company(rs.getLong("company_id"), rs
						.getString("name")));
			}
		}
		return comp;
	}
}
