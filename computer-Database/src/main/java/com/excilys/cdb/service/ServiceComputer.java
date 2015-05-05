package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.page.Page;

public class ServiceComputer implements IServiceComputer {

	private ComputerDao computerDao;

	public ServiceComputer() {
	}

	public long createComputer(Computer c) {
		return computerDao.create(c);
	}

	public Computer findComputer(long id) {
		return computerDao.find(id);
	}

	public void updateComputer(Computer c) {
		computerDao.update(c);
	}

	public void deleteComputer(Computer c) {
		computerDao.delete(c);
	}

	public List<Computer> findAllComputer() {
		return computerDao.findAll();
	}

	public int getCountComputer() {
		return computerDao.getCount();
	}

	@Override
	public int getCountComputer(String pattern) {
		return computerDao.getCount(pattern);
	}

	@Override
	public List<Computer> findAllComputer(Page page, String by, String order) {
		return computerDao
				.findAll(page.getOffset(), page.getRange(), by, order);
	}

	@Override
	public List<Computer> findAllComputer(Page page, String pattern, String by,
			String order) {
		return computerDao.findAll(page.getOffset(), page.getRange(), pattern,
				by, order);
	}

	public ComputerDao getcomputerDao() {
		return computerDao;
	}

	public void setcomputerDao(ComputerDao computerDao) {
		this.computerDao = computerDao;
	}
}
