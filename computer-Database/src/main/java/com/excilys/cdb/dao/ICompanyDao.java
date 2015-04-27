package com.excilys.cdb.dao;

import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.model.Company;

// TODO: Auto-generated Javadoc
/**
 * The Interface CompanyDao.
 */
public interface ICompanyDao {

	/**
	 * Find a company according its id.
	 *
	 * @param id
	 *            the id
	 * @return the company
	 */
	public Company find(long id);

	/**
	 * Delete.
	 *
	 * @param obj
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void delete(Company obj) throws SQLException;

	/**
	 * Find all the companies.
	 *
	 * @return the list
	 */
	public List<Company> findAll();

	/**
	 * Find the range of companies.
	 *
	 * @param offset
	 * @param range
	 * @return the list
	 */
	public List<Company> findAll(int offset, int range);

}
