package com.excilys.cdb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class ComputerMapper {

	/**
	 * Convert a line of the resultSet to an object Computer.
	 *
	 * @param rs
	 * @return the computer
	 */
	public static Computer toComputer(ResultSet rs) throws SQLException {
		Computer computer = null;
		if (rs.next()) {
			computer = new Computer();
			computer.setId(rs.getLong("c_id"));
			computer.setIntroduced(DateMapper.toLocalDateTime(rs
					.getTimestamp("introduced")));

			computer.setDiscontinued(DateMapper.toLocalDateTime(rs
					.getTimestamp("discontinued")));

			computer.setName(rs.getString("c_name"));
			if (rs.getLong("company_id") != 0) {
				computer.setCompany(new Company.CompanyBuilder(rs
						.getString("name")).setId(rs.getLong("company_id"))
						.build());
			}
		}
		return computer;
	}
}
