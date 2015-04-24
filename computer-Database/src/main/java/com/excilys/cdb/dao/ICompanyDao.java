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
	 * Find.
	 *
	 * @param id
	 *            the id
	 * @return the company
	 */
	public Company find(long id);

	public void delete(Company obj) throws SQLException;

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Company> findAll();

	public List<Company> findAll(int offset, int range);

}
