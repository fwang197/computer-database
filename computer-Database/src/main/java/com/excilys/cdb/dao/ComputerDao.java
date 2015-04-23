package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import com.excilys.cdb.dao.jdbc.ConnectionFactory;
import com.excilys.cdb.exception.DaoException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.tools.Tools;
import com.mysql.jdbc.Statement;

// TODO: Auto-generated Javadoc
/**
 * The Enum ConcreteComputerDao.
 */
public enum ComputerDao implements IComputerDao {

	/** The instance. */
	INSTANCE;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.dao.ComputerDao#create(com.excilys.cdb.model.Computer)
	 */
	public long create(Computer comp) {
		PreparedStatement prepare = null;
		Connection conn = null;
		try {
			conn = ConnectionFactory.INSTANCE.getConnection();
			prepare = conn
					.prepareStatement(
							"insert into computer(name,introduced,discontinued,company_id) values (?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
			prepare.setString(1, comp.getName());
			prepare.setTimestamp(2,
					DateMapper.toTimeStamp(comp.getIntroduced()));
			prepare.setTimestamp(3,
					DateMapper.toTimeStamp(comp.getDiscontinued()));
			if (Tools.isNull(comp.getCompany())) {
				prepare.setNull(4, Types.NULL);
			} else {
				prepare.setLong(4, comp.getCompany().getId());
			}
			prepare.executeUpdate();
			ResultSet rs = prepare.getGeneratedKeys();
			if (rs.next()) {
				return rs.getLong(1);
			}
			return -1;
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			Tools.closeProperly(null, prepare);
			ConnectionFactory.INSTANCE.closeConnection(conn);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.dao.ComputerDao#find(long)
	 */
	public Computer find(long id) {

		Computer comp = null;
		PreparedStatement prepare = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = ConnectionFactory.INSTANCE.getConnection();

			prepare = conn
					.prepareStatement("select computer.id as c_id, computer.name as c_name,introduced,discontinued,company_id,company.name "
							+ "from computer left outer join company "
							+ "on computer.company_id = company.id where computer.id = ?");
			prepare.setLong(1, id);
			rs = prepare.executeQuery();
			comp = ComputerMapper.toComputer(rs);
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
	 * @see
	 * com.excilys.cdb.dao.ComputerDao#update(com.excilys.cdb.model.Computer)
	 */
	public void update(Computer comp) {
		PreparedStatement prepare = null;
		Connection conn = null;
		try {
			conn = ConnectionFactory.INSTANCE.getConnection();
			prepare = conn
					.prepareStatement("update computer set name = ?, introduced = ? , discontinued = ? , company_id = ? where id = ?");
			prepare.setString(1, comp.getName());
			prepare.setTimestamp(2,
					DateMapper.toTimeStamp(comp.getIntroduced()));
			prepare.setTimestamp(3,
					DateMapper.toTimeStamp(comp.getDiscontinued()));
			if (Tools.isNull(comp.getCompany())) {
				prepare.setNull(4, Types.NULL);
			} else {
				prepare.setLong(4, comp.getCompany().getId());
			}
			prepare.setLong(5, comp.getId());
			prepare.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			Tools.closeProperly(null, prepare);
			ConnectionFactory.INSTANCE.closeConnection(conn);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.cdb.dao.ComputerDao#delete(com.excilys.cdb.model.Computer)
	 */
	public void delete(Computer comp) {
		PreparedStatement prepare = null;
		Connection conn = null;
		try {
			conn = ConnectionFactory.INSTANCE.getConnection();
			prepare = conn
					.prepareStatement("delete from computer where id = ?");
			prepare.setLong(1, comp.getId());
			prepare.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			Tools.closeProperly(null, prepare);
			ConnectionFactory.INSTANCE.closeConnection(conn);
		}
	}

	public void delete(Computer comp, Connection conn) throws SQLException {
		PreparedStatement prepare = null;
		try {
			conn = ConnectionFactory.INSTANCE.getConnection();
			prepare = conn
					.prepareStatement("delete from computer where id = ?");
			prepare.setLong(1, comp.getId());
			prepare.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			Tools.closeProperly(null, prepare);
			ConnectionFactory.INSTANCE.closeConnection(conn);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.cdb.dao.ComputerDao#findAll()
	 */
	public List<Computer> findAll() {

		LinkedList<Computer> lcomputer = new LinkedList<Computer>();

		PreparedStatement prepare = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = ConnectionFactory.INSTANCE.getConnection();
			prepare = conn
					.prepareStatement("select computer.id as c_id, computer.name as c_name,introduced,discontinued,company_id,company.name "
							+ "from computer left outer join company "
							+ "on computer.company_id = company.id");
			rs = prepare.executeQuery();
			while (!rs.isLast()) {
				lcomputer.add(ComputerMapper.toComputer(rs));
			}
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			Tools.closeProperly(rs, prepare);
			ConnectionFactory.INSTANCE.closeConnection(conn);
		}
		return lcomputer;
	}

	@Override
	public int getCount() {
		int res = 0;
		PreparedStatement prepare = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = ConnectionFactory.INSTANCE.getConnection();
			prepare = conn
					.prepareStatement("select count(*) as nb from computer");
			rs = prepare.executeQuery();
			rs.next();
			res = rs.getInt("nb");
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			Tools.closeProperly(rs, prepare);
			ConnectionFactory.INSTANCE.closeConnection(conn);
		}
		return res;
	}

	@Override
	public int getCount(String pattern) {
		int res = 0;
		PreparedStatement prepare = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = ConnectionFactory.INSTANCE.getConnection();
			prepare = conn
					.prepareStatement("select count(*) as nb "
							+ "from computer left outer join company "
							+ "on computer.company_id = company.id "
							+ "where computer.name like ? "
							+ "or company.name like ? ");
			prepare.setString(1, "%" + pattern + "%");
			prepare.setString(2, "%" + pattern + "%");
			rs = prepare.executeQuery();
			rs.next();
			res = rs.getInt("nb");
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			Tools.closeProperly(rs, prepare);
			ConnectionFactory.INSTANCE.closeConnection(conn);
		}
		return res;
	}

	@Override
	public List<Computer> findAll(int offset, int range, String by, String order) {
		LinkedList<Computer> lcomputer = new LinkedList<Computer>();

		PreparedStatement prepare = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = ConnectionFactory.INSTANCE.getConnection();
			prepare = conn
					.prepareStatement("select computer.id as c_id, computer.name as c_name,introduced,discontinued,company_id,company.name "
							+ "from computer left outer join company "
							+ "on computer.company_id = company.id "
							+ "order by "
							+ by
							+ " "
							+ order
							+ " "
							+ "limit ? offset ?");
			prepare.setInt(1, range);
			prepare.setInt(2, offset);
			rs = prepare.executeQuery();
			if (rs.isBeforeFirst()) {
				while (!rs.isLast()) {
					lcomputer.add(ComputerMapper.toComputer(rs));
				}
			}
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			Tools.closeProperly(rs, prepare);
			ConnectionFactory.INSTANCE.closeConnection(conn);
		}
		return lcomputer;
	}

	@Override
	public List<Computer> findAll(int offset, int range, String pattern,
			String by, String order) {
		LinkedList<Computer> lcomputer = new LinkedList<Computer>();

		PreparedStatement prepare = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = ConnectionFactory.INSTANCE.getConnection();
			prepare = conn
					.prepareStatement("select computer.id as c_id, computer.name as c_name,introduced,discontinued,company_id,company.name "
							+ "from computer left outer join company "
							+ "on computer.company_id = company.id "
							+ "where computer.name like ? or company.name like ? "
							+ "order by "
							+ by
							+ " "
							+ order
							+ " "
							+ "limit ? offset ?");
			prepare.setString(1, "%" + pattern + "%");
			prepare.setString(2, "%" + pattern + "%");
			prepare.setInt(3, range);
			prepare.setInt(4, offset);
			rs = prepare.executeQuery();
			if (rs.isBeforeFirst()) {
				while (!rs.isLast()) {
					lcomputer.add(ComputerMapper.toComputer(rs));
				}
			}
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			Tools.closeProperly(rs, prepare);
			ConnectionFactory.INSTANCE.closeConnection(conn);
		}
		return lcomputer;
	}

	@Override
	public List<Computer> findAll(Company obj) {
		LinkedList<Computer> lcomputer = new LinkedList<Computer>();

		PreparedStatement prepare = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = ConnectionFactory.INSTANCE.getConnection();
			prepare = conn
					.prepareStatement("select computer.id as c_id, computer.name as c_name,introduced,discontinued,company_id,company.name "
							+ "from computer left outer join company "
							+ "on computer.company_id = company.id "
							+ "where company.id = ?");
			prepare.setLong(1, obj.getId());
			rs = prepare.executeQuery();
			while (!rs.isLast()) {
				lcomputer.add(ComputerMapper.toComputer(rs));
			}
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			Tools.closeProperly(rs, prepare);
			ConnectionFactory.INSTANCE.closeConnection(conn);
		}
		return lcomputer;
	}
}
