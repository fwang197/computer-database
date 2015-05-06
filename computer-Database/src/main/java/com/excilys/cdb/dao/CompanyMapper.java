package com.excilys.cdb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cdb.model.Company;

public class CompanyMapper implements RowMapper<Company> {

	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		Company company = null;
		company = new Company.CompanyBuilder(rs.getString("name")).setId(
				rs.getLong("id")).build();
		return company;

	}

}
