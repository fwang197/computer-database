package com.excilys.cdb.clientService;

import java.util.List;

import com.excilys.cdb.model.Company;

/**
 * The Interface IClientCompanyService. Collect the data from web-service
 * company.
 */
public interface IClientCompanyService {

	/**
	 * Gets the company from his Id.
	 *
	 * @param id
	 *            the id
	 * @return the company
	 */
	public Company getCompany(long id);

	/**
	 * Gets all the companies.
	 *
	 * @return List of the Companies.
	 */
	public List<Company> getAll();

	/**
	 * Delete a company by his Id.
	 *
	 * @param id
	 *            the id
	 */
	public void delete(long id);
}
