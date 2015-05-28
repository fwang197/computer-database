package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Company;

/**
 * The Interface IServiceCompany.
 */
public interface ICompanyService {

	/**
	 * Find company according its id.
	 *
	 * @param id
	 *            the id
	 * @return the company
	 */
	public Company findCompany(long id);

	/**
	 * Delete a company. If an operation failed the method must cancel the other
	 * operations.
	 *
	 * @param obj
	 *            the obj
	 */
	public void deleteCompany(Company obj);

	/**
	 * Find all companies.
	 *
	 * @return the list
	 */
	public List<Company> findAllCompany();

	/**
	 * Find a range of companies.
	 *
	 * @param offset
	 *            the offset
	 * @param range
	 *            the range
	 * @return the list
	 */
	public List<Company> findAllCompany(int offset, int range);

}
