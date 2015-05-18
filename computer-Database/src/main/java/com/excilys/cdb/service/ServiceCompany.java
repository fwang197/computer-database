package com.excilys.cdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.dao.CompanyDao;
import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.model.Company;

@Service("serviceCompany")
public class ServiceCompany implements IServiceCompany {

	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private ComputerDao computerDao;

	private ServiceCompany() {
	}

	@Transactional
	public Company findCompany(long id) {
		return companyDao.find(id);
	}

	@Transactional
	public void deleteCompany(Company obj) {
		computerDao.delete(obj);
		companyDao.delete(obj);
	}

	@Transactional
	public List<Company> findAllCompany() {
		return companyDao.findAll();
	}

	@Transactional
	public List<Company> findAllCompany(int offset, int range) {
		return companyDao.findAll(offset, range);
	}

	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public ComputerDao getComputerDao() {
		return computerDao;
	}

	public void setComputerDao(ComputerDao computerDao) {
		this.computerDao = computerDao;
	}

}
