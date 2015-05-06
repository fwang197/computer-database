package com.excilys.cdb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class ComputerMapper implements RowMapper<Computer> {

	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Computer computer = null;
		computer = new Computer.ComputerBuilder(rs.getString("c_name"))
				.setId(rs.getLong("c_id"))
				.setIntroduced(
						DateMapper.toLocalDateTime(rs
								.getTimestamp("introduced")))
				.setDiscontinued(
						DateMapper.toLocalDateTime(rs
								.getTimestamp("discontinued"))).build();
		if (rs.getLong("company_id") != 0) {
			computer.setCompany(new Company.CompanyBuilder(rs.getString("name"))
					.setId(rs.getLong("company_id")).build());
		}

		return computer;
	}
}
