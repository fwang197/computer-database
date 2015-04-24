package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.page.Page;

// TODO: Auto-generated Javadoc
/**
 * The Enum ServiceComputer.
 */
public enum ServiceComputer implements IServiceComputer {

	/** The instance. */
	INSTANCE;

	/**
	 * Creates the computer.
	 *
	 * @param c
	 *            the c
	 */
	public long createComputer(Computer c) {
		return ComputerDao.INSTANCE.create(c);
	}

	/**
	 * Find computer.
	 *
	 * @param id
	 *            the id
	 * @return the computer
	 */
	public Computer findComputer(long id) {
		return ComputerDao.INSTANCE.find(id);
	}

	/**
	 * Update computer.
	 *
	 * @param c
	 *            the c
	 */
	public void updateComputer(Computer c) {
		ComputerDao.INSTANCE.update(c);
	}

	/**
	 * Delete computer.
	 *
	 * @param c
	 *            the c
	 */
	public void deleteComputer(Computer c) {
		ComputerDao.INSTANCE.delete(c);
	}

	/**
	 * Find all computer.
	 *
	 * @return the list
	 */
	public List<Computer> findAllComputer() {
		return ComputerDao.INSTANCE.findAll();
	}

	public int getCountComputer() {
		return ComputerDao.INSTANCE.getCount();
	}

	@Override
	public int getCountComputer(String pattern) {
		return ComputerDao.INSTANCE.getCount(pattern);
	}

	@Override
	public List<Computer> findAllComputer(Page page, String by, String order) {
		return ComputerDao.INSTANCE.findAll(page.getOffset(), page.getRange(),
				by, order);
	}

	@Override
	public List<Computer> findAllComputer(Page page, String pattern, String by,
			String order) {
		return ComputerDao.INSTANCE.findAll(page.getOffset(), page.getRange(),
				pattern, by, order);
	}
}
