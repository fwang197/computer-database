package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.model.Computer;

// TODO: Auto-generated Javadoc
/**
 * The Enum ServiceComputer.
 */
public enum ServiceComputer {

	/** The instance. */
	INSTANCE;

	/**
	 * Creates the computer.
	 *
	 * @param c
	 *            the c
	 */
	public void createComputer(Computer c) {
		ComputerDao.INSTANCE.create(c);
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

	public List<Computer> findAllRangeComputer(int offset, int range) {
		return ComputerDao.INSTANCE.findAllRange(offset, range);
	}
}
