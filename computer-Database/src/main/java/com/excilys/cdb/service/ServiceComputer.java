package com.excilys.cdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.dao.ComputerDao.Order;
import com.excilys.cdb.dao.ComputerDao.Row;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.page.Page;

@Service("serviceComputer")
public class ServiceComputer implements IServiceComputer {

	@Autowired
	private ComputerDao computerDao;

	public ServiceComputer() {
	}

	@Transactional
	public void createComputer(Computer c) {
		computerDao.create(c);
	}

	@Transactional
	public Computer findComputer(long id) {
		return computerDao.find(id);
	}

	@Transactional
	public void updateComputer(Computer c) {
		computerDao.update(c);
	}

	@Transactional
	public void deleteComputer(Computer c) {
		computerDao.delete(c);
	}

	@Transactional
	public List<Computer> findAllComputer() {
		return computerDao.findAll();
	}

	@Transactional
	public int getCountComputer() {
		return computerDao.getCount();
	}

	@Transactional
	public int getCountComputer(String pattern) {
		return computerDao.getCount(pattern);
	}

	@Transactional
	public List<Computer> findAllComputer(Page page) {
		if (page.getSearch().isEmpty()) {
			return computerDao.findAll(page.getOffset(), page.getRange(),
					Row.getRow(page.getField()),
					Order.getOrder(page.getOrder()));
		}
		return computerDao.findAll(page.getOffset(), page.getRange(),
				page.getSearch(), Row.getRow(page.getField()),
				Order.getOrder(page.getOrder()));
	}

	public ComputerDao getcomputerDao() {
		return computerDao;
	}

	public void setcomputerDao(ComputerDao computerDao) {
		this.computerDao = computerDao;
	}
}
