package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.dao.CompanyDao;
import com.excilys.cdb.model.Company;

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
