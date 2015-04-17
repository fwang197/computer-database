package main.java.com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import main.java.com.excilys.cdb.jdbc.ConnectionFactory;
import main.java.com.excilys.cdb.mapper.Mapper;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.tools.Tools;

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
			comp = Mapper.toCompany(rs);
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			Tools.closeProperly(rs, prepare);
			ConnectionFactory.INSTANCE.closeConnection(conn);
		}
		return comp;
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
				lcompany.add(Mapper.toCompany(rs));
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
	public List<Company> findAllRange(int offset, int range) {
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
					lcompany.add(Mapper.toCompany(rs));
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
