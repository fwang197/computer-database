package com.excilys.cdb.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.dao.CompanyDao;
import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.dao.jdbc.ConnectionFactory;
import com.excilys.cdb.exception.ServiceException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.tools.Tools;

// TODO: Auto-generated Javadoc
/**
 * The Enum ServiceCompany.
 */
public enum ServiceCompany implements IServiceCompany {

	/** The instance. */
	INSTANCE;

	/**
	 * Find company.
	 *
	 * @param id
	 *            the id
	 * @return the company
	 */
	public Company findCompany(long id) {
		return CompanyDao.INSTANCE.find(id);
	}

	public void deleteCompany(Company obj) {
		PreparedStatement prepare = null;
		Connection conn = null;
		try {
			conn = ConnectionFactory.INSTANCE.getConnection();
			conn.setAutoCommit(false);
			for (Computer comp : ComputerDao.INSTANCE.findAll(obj)) {
				ComputerDao.INSTANCE.delete(comp, conn);
			}
			CompanyDao.INSTANCE.delete(obj, conn);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ServiceException();
			}
			throw new ServiceException();
		} finally {
			Tools.closeProperly(null, prepare);
			ConnectionFactory.INSTANCE.closeConnection(conn);
		}
	}

	/**
	 * Find all company.
	 *
	 * @return the list
	 */
	public List<Company> findAllCompany() {
		return CompanyDao.INSTANCE.findAll();
	}

	public List<Company> findAllCompany(int offset, int range) {
		return CompanyDao.INSTANCE.findAll(offset, range);
	}

}
