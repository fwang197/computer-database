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
	 * Creates the computer.
	 *
	 * @param obj
	 * @return the id
	 */
	public long create(Computer obj);

	/**
	 * Find a computer according to its id.
	 *
	 * @param id
	 * @return the computer
	 */
	public Computer find(long id);

	/**
	 * Update.
	 *
	 * @param obj
	 */
	public void update(Computer obj);

	/**
	 * Delete.
	 *
	 * @param obj
	 */
	public void delete(Computer obj);

	/**
	 * Delete without connection.
	 *
	 * @param obj
	 * @throws SQLException
	 */
	public void deleteWithoutConnection(Computer obj) throws SQLException;

	/**
	 * Gets the count of computers.
	 *
	 * @return the count
	 */
	public int getCount();

	/**
	 * Gets the count of computers according to a pattern in the companies or
	 * computers name.
	 *
	 * @param pattern
	 * @return the count
	 */
	public int getCount(String pattern);

	/**
	 * Find all computers.
	 *
	 * @return the list
	 */
	public List<Computer> findAll();

	/**
	 * Find a range of computers order by column name and its way (ASC or DESC).
	 *
	 * @param offset
	 * @param range
	 * @param by
	 * @param order
	 * @return the list
	 */
	public List<Computer> findAll(int offset, int range, String by, String order);

	/**
	 * Find a range of computers according to a pattern and order by column name
	 * and its way (ASC or DESC).
	 *
	 * @param offset
	 * @param range
	 * @param pattern
	 * @param by
	 * @param order
	 * @return the list
	 */
	public List<Computer> findAll(int offset, int range, String pattern,
			String by, String order);

	/**
	 * Find all the computers who belong to a company.
	 *
	 * @param obj
	 *            the obj
	 * @return the list
	 */
	public List<Computer> findAll(Company obj);

}