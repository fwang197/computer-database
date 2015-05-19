package com.excilys.cdb.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.exception.DaoException;
import com.excilys.cdb.model.Company;

@Repository("companyDao")
public class CompanyDao implements IDao<Company> {

	@Autowired
	private SessionFactory sessionFactory;

	private final Logger logger = LoggerFactory.getLogger(CompanyDao.class);

	private CompanyDao() {
	}

	public Company find(long id) {
		Company company = null;
		try {
			return (Company) this.sessionFactory.getCurrentSession()
					.createQuery("from Company where id = ?")
					.setParameter(0, id).uniqueResult();

		} catch (DataAccessException e) {
			logger.error("Find Company error : {} " + e.getMessage(), id);
		}
		return company;
	}

	public void delete(Company comp) {

		try {
			this.sessionFactory.getCurrentSession()
					.createQuery("delete from Company where id = ?")
					.setParameter(0, comp.getId()).executeUpdate();
		} catch (DataAccessException e) {
			logger.error("Delete Company error : {} ", comp);
			throw new DaoException();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Company> findAll() {

		List<Company> lcompany = new LinkedList<Company>();

		try {
			lcompany = this.sessionFactory.getCurrentSession()
					.createQuery("from Company").list();
		} catch (DataAccessException e) {
			logger.error("Find all Company error");
			throw new DaoException();
		}
		return lcompany;
	}

	@SuppressWarnings("unchecked")
	public List<Company> findAll(int offset, int range) {
		List<Company> lcompany = new LinkedList<Company>();

		try {
			lcompany = this.sessionFactory.getCurrentSession()
					.createQuery("from Company").setFirstResult(offset)
					.setMaxResults(range).list();
		} catch (DataAccessException e) {
			logger.error("Find all range Company error : {} to {} ", offset,
					offset + range);
			throw new DaoException();
		}
		return lcompany;
	}
}
