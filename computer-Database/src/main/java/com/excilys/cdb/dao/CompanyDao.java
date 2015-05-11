package com.excilys.cdb.dao;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.exception.DaoException;
import com.excilys.cdb.model.Company;

@Repository("companyDao")
public class CompanyDao implements IDao<Company> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final Logger logger = LoggerFactory.getLogger(CompanyDao.class);

	private CompanyDao() {
	}

	public Company find(long id) {
		Company company = null;
		try {

			company = this.jdbcTemplate.queryForObject(
					"select * from company where id = ?", new Object[] { id },
					new CompanyMapper());
		} catch (DataAccessException e) {
			logger.error("Find Company error : {} " + e.getMessage(), id);
		}
		return company;
	}

	public void delete(Company comp) {

		try {
			this.jdbcTemplate.update("delete from company where id = ?",
					comp.getId());
		} catch (DataAccessException e) {
			logger.error("Delete Company error : {} ", comp);
			throw new DaoException();
		}
	}

	public List<Company> findAll() {

		List<Company> lcompany = new LinkedList<Company>();

		try {
			lcompany = this.jdbcTemplate.query("select * from company",
					new CompanyMapper());
		} catch (DataAccessException e) {
			logger.error("Find all Company error");
			throw new DaoException();
		}
		return lcompany;
	}

	public List<Company> findAll(int offset, int range) {
		List<Company> lcompany = new LinkedList<Company>();

		try {

			lcompany = this.jdbcTemplate.query(
					"select * from company limit ? offset ?", new Object[] {
							range, offset }, new CompanyMapper());
		} catch (DataAccessException e) {
			logger.error("Find all range Company error : {} to {} ", offset,
					offset + range);
			throw new DaoException();
		}
		return lcompany;
	}
}
