package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.dao.jdbc.ConnectionFactory;
import com.excilys.cdb.exception.DaoException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.tools.Tools;

// TODO: Auto-generated Javadoc
/**
 * The Enum ConcreteCompanyDao.
 */
public enum CompanyDao implements IDao<Company> {

	/** The instance. */
	INSTANCE;
	private final Logger logger = LoggerFactory.getLogger(CompanyDao.class);

	public Company find(long id) {
		Company company = null;
		PreparedStatement prepare = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = ConnectionFactory.INSTANCE.getConnection();
			prepare = conn
					.prepareStatement("select * from company where id = ?");
			prepare.setLong(1, id);
			rs = prepare.executeQuery();
			company = CompanyMapper.toCompany(rs);
		} catch (SQLException e) {
			logger.error("Find Company error : {} ", id);
			throw new DaoException();
		} finally {
			Tools.closeProperly(rs, prepare);
			ConnectionFactory.INSTANCE.closeConnection();
		}
		return company;
	}

	public void deleteWithoutConnection(Company comp) throws SQLException {
		PreparedStatement prepare = null;
		Connection conn = null;
		try {
			conn = ConnectionFactory.INSTANCE.getConnection();
			prepare = conn.prepareStatement("delete from company where id = ?");
			prepare.setLong(1, comp.getId());

			prepare.executeUpdate();
		} catch (SQLException e) {
			logger.error("Delete Company error : {} ", comp);
			throw new SQLException();
		} finally {
			Tools.closeProperly(null, prepare);
		}
	}

	public List<Company> findAll() {

		LinkedList<Company> lcompany = new LinkedList<Company>();

		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = ConnectionFactory.INSTANCE.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from company");
			while (!rs.isLast()) {
				lcompany.add(CompanyMapper.toCompany(rs));
			}
		} catch (SQLException e) {
			logger.error("Find all Company error");
			throw new DaoException();
		} finally {
			Tools.closeProperly(rs, stmt);
			ConnectionFactory.INSTANCE.closeConnection();
		}
		return lcompany;
	}

	public List<Company> findAll(int offset, int range) {
		LinkedList<Company> lcompany = new LinkedList<Company>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = ConnectionFactory.INSTANCE.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from company limit " + range
					+ " offset " + offset);
			if (rs.first()) {
				while (!rs.isLast()) {
					lcompany.add(CompanyMapper.toCompany(rs));
				}
			}
		} catch (SQLException e) {
			logger.error("Find all range Company error : {} to {} ", offset,
					offset + range);
			throw new DaoException();
		} finally {
			Tools.closeProperly(rs, stmt);
			ConnectionFactory.INSTANCE.closeConnection();
		}
		return lcompany;
	}
}
