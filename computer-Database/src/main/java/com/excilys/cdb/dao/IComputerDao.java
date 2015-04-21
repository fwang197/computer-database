package com.excilys.cdb.dao;

import java.util.List;

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
	public void create(Computer obj);

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

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Computer> findAll();

	public List<Computer> findAllRange(int offset, int range);

	public int getCount();
}