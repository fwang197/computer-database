package com.excilys.cdb.clientService;

import java.util.List;

import com.excilys.cdb.model.Company;

public interface IClientCompanyService {

	public Company getCompany(long id);

	public List<Company> getAll();

	public void delete(long id);
}
