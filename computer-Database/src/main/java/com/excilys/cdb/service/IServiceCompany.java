package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Company;

public interface IServiceCompany {
	public Company findCompany(long id);

	public List<Company> findAllCompany();

	public List<Company> findAllRangeCompany(int offset, int range);
}
