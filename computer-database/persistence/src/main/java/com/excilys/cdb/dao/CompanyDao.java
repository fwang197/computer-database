package com.excilys.cdb.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Company;

@Repository("companyDao")
public class CompanyDao implements IDao<Company> {

	@Autowired
	private SessionFactory sessionFactory;

	private CompanyDao() {
	}

	public Company find(long id) {

		return (Company) this.sessionFactory.getCurrentSession()
				.createQuery("from Company where id = ?").setParameter(0, id)
				.uniqueResult();

	}

	public void delete(Company comp) {

		this.sessionFactory.getCurrentSession()
				.createQuery("delete from Company where id = ?")
				.setParameter(0, comp.getId()).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Company> findAll() {

		return this.sessionFactory.getCurrentSession()
				.createQuery("from Company").list();

	}

	@SuppressWarnings("unchecked")
	public List<Company> findAll(int offset, int range) {

		return this.sessionFactory.getCurrentSession()
				.createQuery("from Company").setFirstResult(offset)
				.setMaxResults(range).list();

	}
}
