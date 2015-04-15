package com.excilys.cdb.dao;

import java.util.List;

import com.excilys.cdb.model.Company;

// TODO: Auto-generated Javadoc
/**
 * The Interface CompanyDao.
 */
public interface CompanyDao {

	/**
	 * Find.
	 *
	 * @param id the id
	 * @return the company
	 */
	public Company find(long id);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Company> findAll();
}
