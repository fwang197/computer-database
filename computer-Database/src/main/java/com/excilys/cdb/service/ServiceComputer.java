package com.excilys.cdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.page.Page;

@Service("serviceComputer")
public class ServiceComputer implements IServiceComputer {

	@Autowired
	private ComputerDao computerDao;

	public ServiceComputer() {
	}

	public void createComputer(Computer c) {
		computerDao.create(c);
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
	public List<Computer> findAllComputer(Page page) {
		if (page.getSearch().isEmpty()) {
			return computerDao.findAll(page.getOffset(), page.getRange(),
					page.getField(), page.getOrder());
		}
		return computerDao.findAll(page.getOffset(), page.getRange(),
				page.getSearch(), page.getField(), page.getOrder());
	}

	public ComputerDao getcomputerDao() {
		return computerDao;
	}

	public void setcomputerDao(ComputerDao computerDao) {
		this.computerDao = computerDao;
	}
}
