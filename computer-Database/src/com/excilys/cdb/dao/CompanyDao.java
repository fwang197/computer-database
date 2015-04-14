package com.excilys.cdb.dao;

import java.util.List;

import com.excilys.cdb.model.Company;

public interface CompanyDao {

	public Company find(long id);

	public List<Company> findAll();
}
