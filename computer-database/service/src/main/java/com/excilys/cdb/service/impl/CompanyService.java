package com.excilys.cdb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.dao.impl.CompanyDao;
import com.excilys.cdb.dao.impl.ComputerDao;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.ICompanyService;

@Service("companyService")
@Transactional
public class CompanyService implements ICompanyService {

	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private ComputerDao computerDao;

	private CompanyService() {
	}

	public Company findCompany(long id) {
		return companyDao.find(id);
	}

	public void deleteCompany(Company obj) {
		computerDao.delete(obj);
		companyDao.delete(obj);
	}

	public List<Company> findAllCompany() {
		return companyDao.findAll();
	}

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
