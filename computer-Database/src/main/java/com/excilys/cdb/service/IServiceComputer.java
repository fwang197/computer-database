package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.page.Page;

// TODO: Auto-generated Javadoc
/**
 * The Interface IServiceComputer.
 */
public interface IServiceComputer {

	/**
	 * Creates the computer.
	 *
	 * @param c
	 * @return the long
	 */
	public long createComputer(Computer c);

	/**
	 * Find computer.
	 *
	 * @param id
	 * @return the computer
	 */
	public Computer findComputer(long id);

	/**
	 * Update computer.
	 *
	 * @param c
	 */
	public void updateComputer(Computer c);

	/**
	 * Delete computer.
	 *
	 * @param c
	 */
	public void deleteComputer(Computer c);

	/**
	 * Gets the count computer.
	 *
	 * @return the count computer
	 */
	public int getCountComputer();

	/**
	 * Gets the count computer according to a pattern.
	 *
	 * @param pattern
	 * @return the count computer
	 */
	public int getCountComputer(String pattern);

	/**
	 * Find all computers.
	 *
	 * @return the list
	 */
	public List<Computer> findAllComputer();

	/**
	 * Find the computers that the page should have order by a column name and
	 * its ways (ASC or DESC).
	 *
	 * @param page
	 * @param by
	 * @param order
	 * @return the list
	 */
	public List<Computer> findAllComputer(Page page, String by, String order);

	/**
	 * Find the computers that the page should have according to a patter order
	 * by a column name and its ways (ASC or DESC).
	 *
	 * @param page
	 * @param pattern
	 * @param by
	 * @param order
	 * @return the list
	 */
	public List<Computer> findAllComputer(Page page, String pattern, String by,
			String order);

}
