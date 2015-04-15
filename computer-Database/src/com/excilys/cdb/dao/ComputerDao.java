package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.excilys.cdb.jdbc.ConnectionFactory;
import com.excilys.cdb.mapper.Mapper;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.tools.Tools;

// TODO: Auto-generated Javadoc
/**
 * The Enum ConcreteComputerDao.
 */
public enum ComputerDao implements IComputerDao {
	
	/** The instance. */
	INSTANCE;

	/* (non-Javadoc)
	 * @see com.excilys.cdb.dao.ComputerDao#create(com.excilys.cdb.model.Computer)
	 */
	public void create(Computer comp) {
		PreparedStatement prepare = null;
		Connection conn = null;
		try {
			conn = ConnectionFactory.INSTANCE.getConnection();
			prepare = conn
					.prepareStatement("insert into computer(name,introduced,discontinued,company_id) values (?,?,?,?)");
			prepare.setString(1, comp.getName());
			prepare.setTimestamp(2, Mapper.toTimeStamp(comp.getIntroduced()));
			prepare.setTimestamp(3, Mapper.toTimeStamp(comp.getDiscontinued()));
			prepare.setLong(4, comp.getCompany().getId());
			prepare.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			Tools.closeProperly(null, prepare);
			ConnectionFactory.INSTANCE.closeConnection(conn);
		}
	}

	/* (non-Javadoc)
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
			comp = Mapper.toComputer(rs);
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			Tools.closeProperly(rs, prepare);
			ConnectionFactory.INSTANCE.closeConnection(conn);
		}
		return comp;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.dao.ComputerDao#update(com.excilys.cdb.model.Computer)
	 */
	public void update(Computer comp) {
		PreparedStatement prepare = null;
		Connection conn = null;
		try {
			conn = ConnectionFactory.INSTANCE.getConnection();
			prepare = conn
					.prepareStatement("update computer set name = ?, introduced = ? , discontinued = ? , company_id = ? where id = ?");
			prepare.setString(1, comp.getName());
			prepare.setTimestamp(2, Mapper.toTimeStamp(comp.getIntroduced()));
			prepare.setTimestamp(3, Mapper.toTimeStamp(comp.getDiscontinued()));
			prepare.setLong(4, comp.getCompany().getId());
			prepare.setLong(5, comp.getId());
			prepare.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			Tools.closeProperly(null, prepare);
			ConnectionFactory.INSTANCE.closeConnection(conn);
		}
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.dao.ComputerDao#delete(com.excilys.cdb.model.Computer)
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

	/* (non-Javadoc)
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
				lcomputer.add(Mapper.toComputer(rs));
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
