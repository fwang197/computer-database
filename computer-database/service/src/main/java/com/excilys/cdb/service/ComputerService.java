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

@Service("computerService")
@Transactional
public class ComputerService implements IComputerService {

	@Autowired
	private ComputerDao computerDao;

	public ComputerService() {
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

	public long getCountComputer() {
		return computerDao.getCount();
	}

	public long getCountComputer(String pattern) {
		return computerDao.getCount(pattern);
	}

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
