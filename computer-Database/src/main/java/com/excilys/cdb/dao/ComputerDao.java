package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.dao.jdbc.ConnectionFactory;
import com.excilys.cdb.exception.DaoException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.tools.Tools;

@Repository("computerDao")
public class ComputerDao implements IDao<Computer> {

	@Autowired
	private ConnectionFactory connectionFact;

	private final Logger logger = LoggerFactory.getLogger(ComputerDao.class);

	public ComputerDao() {
	}

	public long create(Computer comp) {
		PreparedStatement prepare = null;
		Connection conn = null;
		try {
			conn = connectionFact.getConnection();
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
			logger.error("Create Computer error : {} ", comp);
			throw new DaoException();
		} finally {
			Tools.closeProperly(null, prepare);
		}
	}

	public Computer find(long id) {

		Computer computer = null;
		PreparedStatement prepare = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = connectionFact.getConnection();

			prepare = conn
					.prepareStatement("select computer.id as c_id, computer.name as c_name,introduced,discontinued,company_id,company.name "
							+ "from computer left outer join company "
							+ "on computer.company_id = company.id where computer.id = ?");
			prepare.setLong(1, id);
			rs = prepare.executeQuery();
			computer = ComputerMapper.toComputer(rs);
		} catch (SQLException e) {
			logger.error("Find Computer error : {} ");
			throw new DaoException();
		} finally {
			Tools.closeProperly(rs, prepare);
		}
		return computer;
	}

	public void update(Computer comp) {
		PreparedStatement prepare = null;
		Connection conn = null;
		try {
			conn = connectionFact.getConnection();
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
			logger.error("Update Computer error : {} ", comp);
			throw new DaoException();
		} finally {
			Tools.closeProperly(null, prepare);
		}
	}

	public void delete(Company comp) {
		PreparedStatement prepare = null;
		Connection conn = null;
		try {
			conn = connectionFact.getConnection();
			prepare = conn
					.prepareStatement("delete from computer where company_id = ?");
			prepare.setLong(1, comp.getId());
			prepare.executeUpdate();
		} catch (SQLException e) {
			logger.error("Delete Computer error : {} ", comp);
			throw new DaoException();
		} finally {
			Tools.closeProperly(null, prepare);
		}
	}

	public void delete(Computer comp) {
		PreparedStatement prepare = null;
		Connection conn = null;
		try {
			conn = connectionFact.getConnection();
			prepare = conn
					.prepareStatement("delete from computer where id = ?");
			prepare.setLong(1, comp.getId());
			prepare.executeUpdate();
		} catch (SQLException e) {
			logger.error("Delete Computer error : {} ", comp);
			throw new DaoException();
		} finally {
			Tools.closeProperly(null, prepare);
		}
	}

	public List<Computer> findAll() {

		LinkedList<Computer> lcomputer = new LinkedList<Computer>();

		PreparedStatement prepare = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = connectionFact.getConnection();
			prepare = conn
					.prepareStatement("select computer.id as c_id, computer.name as c_name,introduced,discontinued,company_id,company.name "
							+ "from computer left outer join company "
							+ "on computer.company_id = company.id");
			rs = prepare.executeQuery();
			while (!rs.isLast()) {
				lcomputer.add(ComputerMapper.toComputer(rs));
			}
		} catch (SQLException e) {
			logger.error("Find all Computer error");
			throw new DaoException();
		} finally {
			Tools.closeProperly(rs, prepare);
		}
		return lcomputer;
	}

	public int getCount() {
		int res = 0;
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = connectionFact.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) as nb from computer");
			rs.next();
			res = rs.getInt("nb");
		} catch (SQLException e) {
			logger.error("Get all Computer count error");
			throw new DaoException();
		} finally {
			Tools.closeProperly(rs, stmt);
		}
		return res;
	}

	public int getCount(String pattern) {
		int res = 0;
		PreparedStatement prepare = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = connectionFact.getConnection();
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
			logger.error("Get all Computer with pattern {} error", pattern);
			throw new DaoException();
		} finally {
			Tools.closeProperly(rs, prepare);
		}
		return res;
	}

	public List<Computer> findAll(int offset, int range, String by, String order) {
		LinkedList<Computer> lcomputer = new LinkedList<Computer>();

		PreparedStatement prepare = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = connectionFact.getConnection();
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
			logger.error("Find all range Computer like {} order by {} ", by,
					order);
			throw new DaoException();
		} finally {
			Tools.closeProperly(rs, prepare);
		}
		return lcomputer;
	}

	public List<Computer> findAll(int offset, int range, String pattern,
			String by, String order) {
		LinkedList<Computer> lcomputer = new LinkedList<Computer>();

		PreparedStatement prepare = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = connectionFact.getConnection();
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
			logger.error(
					"Find all range Computer like {} order by {} with pattern {} ",
					by, order, pattern);
			throw new DaoException();
		} finally {
			Tools.closeProperly(rs, prepare);
		}
		return lcomputer;
	}

	public List<Computer> findAll(Company obj) {
		LinkedList<Computer> lcomputer = new LinkedList<Computer>();

		PreparedStatement prepare = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = connectionFact.getConnection();
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
			logger.error("Find all Computer with a Company : {} ", obj);
			throw new DaoException();
		} finally {
			Tools.closeProperly(rs, prepare);
		}
		return lcomputer;
	}

	public ConnectionFactory getConnectionFact() {
		return connectionFact;
	}

	public void setConnectionFact(ConnectionFactory connectionFact) {
		this.connectionFact = connectionFact;
	}
}
