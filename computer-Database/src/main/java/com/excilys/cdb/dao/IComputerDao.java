package com.excilys.cdb.dao;

import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

// TODO: Auto-generated Javadoc
/**
 * The Interface ComputerDao.
 */
public interface IComputerDao {

	/**
	 * Creates the.
	 *
	 * @param obj
	 *            the obj
	 */
	public long create(Computer obj);

	/**
	 * Find.
	 *
	 * @param id
	 *            the id
	 * @return the computer
	 */
	public Computer find(long id);

	/**
	 * Update.
	 *
	 * @param obj
	 *            the obj
	 */
	public void update(Computer obj);

	/**
	 * Delete.
	 *
	 * @param obj
	 *            the obj
	 */
	public void delete(Computer obj);

	public void deleteWithoutConnection(Computer obj) throws SQLException;

	public int getCount();

	public int getCount(String pattern);

	public List<Computer> findAll();

	public List<Computer> findAll(int offset, int range, String by, String order);

	public List<Computer> findAll(int offset, int range, String pattern,
			String by, String order);

	public List<Computer> findAll(Company obj);

}