package com.excilys.cdb.service;

import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.model.Company;

public interface IServiceCompany {
	public Company findCompany(long id) throws SQLException;

	public void deleteCompany(Company obj);

	public List<Company> findAllCompany();

	public List<Company> findAllCompany(int offset, int range);

}