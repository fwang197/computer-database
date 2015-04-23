package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.excilys.cdb.dao.jdbc.ConnectionFactory;
import com.excilys.cdb.exception.DaoException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.tools.Tools;

// TODO: Auto-generated Javadoc
/**
 * The Enum ConcreteCompanyDao.
 */
public enum CompanyDao implements ICompanyDao {

	/** The instance. */
	INSTANCE;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.dao.CompanyDao#find(long)
	 */
	public Company find(long id) {
		Company comp = null;
		PreparedStatement prepare = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = ConnectionFactory.INSTANCE.getConnection();
			prepare = conn
					.prepareStatement("select * from company where id = ?");
			prepare.setLong(1, id);
			rs = prepare.executeQuery();
			comp = CompanyMapper.toCompany(rs);
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			Tools.closeProperly(rs, prepare);
			ConnectionFactory.INSTANCE.closeConnection(conn);
		}
		return comp;
	}

	public void delete(Company comp, Connection conn) throws SQLException {
		PreparedStatement prepare = null;
		try {
			conn = ConnectionFactory.INSTANCE.getConnection();
			prepare = conn.prepareStatement("delete from company where id = ?");
			prepare.setLong(1, comp.getId());

			prepare.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			Tools.closeProperly(null, prepare);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.dao.CompanyDao#findAll()
	 */
	public List<Company> findAll() {

		LinkedList<Company> lcompany = new LinkedList<Company>();

		PreparedStatement prepare = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = ConnectionFactory.INSTANCE.getConnection();
			prepare = conn.prepareStatement("select * from company");
			rs = prepare.executeQuery();
			while (!rs.isLast()) {
				lcompany.add(CompanyMapper.toCompany(rs));
			}
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			Tools.closeProperly(rs, prepare);
			ConnectionFactory.INSTANCE.closeConnection(conn);
		}
		return lcompany;
	}

	@Override
	public List<Company> findAll(int offset, int range) {
		LinkedList<Company> lcompany = new LinkedList<Company>();
		PreparedStatement prepare = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = ConnectionFactory.INSTANCE.getConnection();
			prepare = conn.prepareStatement("select * from company limit "
					+ range + " offset " + offset);
			rs = prepare.executeQuery();
			if (rs.first()) {
				while (!rs.isLast()) {
					lcompany.add(CompanyMapper.toCompany(rs));
				}
			}
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			Tools.closeProperly(rs, prepare);
			ConnectionFactory.INSTANCE.closeConnection(conn);
		}
		return lcompany;
	}

}
