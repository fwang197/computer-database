package com.excilys.cdb.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.dao.CompanyDao;
import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.dao.jdbc.ConnectionFactory;
import com.excilys.cdb.exception.ServiceException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.tools.Tools;

public class ServiceCompany implements IServiceCompany {

	private ConnectionFactory connectionFact;

	private CompanyDao companyDao;

	private ComputerDao computerDao;

	private final Logger logger = LoggerFactory.getLogger(ServiceCompany.class);

	public Company findCompany(long id) {
		return companyDao.find(id);
	}

	private ServiceCompany() {
	}

	public void deleteCompany(Company obj) {
		PreparedStatement prepare = null;
		connectionFact.getConnection();
		try {
			connectionFact.startTransaction();
			for (Computer comp : computerDao.findAll(obj)) {
				computerDao.deleteWithoutConnection(comp);
			}
			companyDao.deleteWithoutConnection(obj);
			connectionFact.commit();
		} catch (SQLException e) {
			logger.error("Service Company : delete Company error : {} ", obj);
			connectionFact.rollback();
			throw new ServiceException();
		} finally {
			Tools.closeProperly(null, prepare);
			connectionFact.closeConnection();
		}
	}

	public List<Company> findAllCompany() {
		return companyDao.findAll();
	}

	public List<Company> findAllCompany(int offset, int range) {
		return companyDao.findAll(offset, range);
	}

	public ConnectionFactory getConnectionFact() {
		return connectionFact;
	}

	public void setConnectionFact(ConnectionFactory connectionFact) {
		this.connectionFact = connectionFact;
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
