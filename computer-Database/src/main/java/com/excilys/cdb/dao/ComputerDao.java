package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.exception.DaoException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.tools.Tools;

@Repository("computerDao")
public class ComputerDao implements IDao<Computer> {

	private JdbcTemplate jdbcTemplate;

	private final Logger logger = LoggerFactory.getLogger(ComputerDao.class);

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public ComputerDao() {
	}

	public long create(Computer comp) {
		long res = -1;
		PreparedStatementCreator preparestatementcreator = new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement prepare = conn
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
				return prepare;
			}
		};

		try {
			KeyHolder holder = new GeneratedKeyHolder();
			this.jdbcTemplate.update(preparestatementcreator, holder);
			res = holder.getKey().longValue();
		} catch (DataAccessException e) {
			logger.error("Create Computer error : {} ", comp);
			throw new DaoException();
		}
		return res;
	}

	public Computer find(long id) {

		Computer computer = null;
		try {
			computer = this.jdbcTemplate
					.queryForObject(
							"select computer.id as c_id, computer.name as c_name,introduced,discontinued,company_id,company.name "
									+ "from computer left outer join company "
									+ "on computer.company_id = company.id where computer.id = ?",
							new Object[] { id }, new ComputerMapper());
		} catch (DataAccessException e) {
			logger.error("Find Computer error : {} " + e.getMessage());
		}
		return computer;
	}

	public void update(Computer comp) {

		try {
			this.jdbcTemplate
					.update("update computer set name = ?, introduced = ? , discontinued = ? , company_id = ? where id = ?",
							comp.getName(), DateMapper.toTimeStamp(comp
									.getIntroduced()), DateMapper
									.toTimeStamp(comp.getDiscontinued()), Tools
									.isNull(comp.getCompany()) ? Types.NULL
									: comp.getCompany().getId(), comp.getId());
		} catch (DataAccessException e) {
			logger.error("Update Computer error : {} ", comp);
			throw new DaoException();
		}
	}

	public void delete(Company comp) {
		try {
			this.jdbcTemplate.update(
					"delete from computer where company_id = ?", comp.getId());
		} catch (DataAccessException e) {
			logger.error("Delete Computer error : {} ", comp);
			throw new DaoException();
		}
	}

	public void delete(Computer comp) {
		try {
			this.jdbcTemplate.update("delete from computer where id = ?",
					comp.getId());
		} catch (DataAccessException e) {
			logger.error("Delete Computer error : {} ", comp);
			throw new DaoException();
		}
	}

	public int getCount() {
		int res = 0;
		try {
			res = this.jdbcTemplate.queryForObject(
					"select count(*) from computer", Integer.class);
		} catch (DataAccessException e) {
			logger.error("Get all Computer count error");
			throw new DaoException();
		}
		return res;
	}

	public int getCount(String pattern) {
		int res = 0;
		try {
			res = this.jdbcTemplate.queryForObject(
					"select count(*) as nb "
							+ "from computer left outer join company "
							+ "on computer.company_id = company.id "
							+ "where computer.name like ? "
							+ "or company.name like ? ", Integer.class, "%"
							+ pattern + "%", "%" + pattern + "%");
		} catch (DataAccessException e) {
			logger.error("Get all Computer with pattern {} error", pattern);
			throw new DaoException();
		}
		return res;
	}

	public List<Computer> findAll() {

		List<Computer> lcomputer = new LinkedList<Computer>();

		try {
			lcomputer = this.jdbcTemplate
					.query("select computer.id as c_id, computer.name as c_name,introduced,discontinued,company_id,company.name "
							+ "from computer left outer join company "
							+ "on computer.company_id = company.id",
							new ComputerMapper());
		} catch (DataAccessException e) {
			logger.error("Find all Computer error");
			throw new DaoException();
		}
		return lcomputer;
	}

	public List<Computer> findAll(int offset, int range, String by, String order) {
		List<Computer> lcomputer = new LinkedList<Computer>();

		try {
			lcomputer = this.jdbcTemplate
					.query("select computer.id as c_id, computer.name as c_name,introduced,discontinued,company_id,company.name "
							+ "from computer left outer join company "
							+ "on computer.company_id = company.id "
							+ "order by "
							+ by
							+ " "
							+ order
							+ " "
							+ "limit ? offset ?",
							new Object[] { range, offset },
							new ComputerMapper());
		} catch (DataAccessException e) {
			logger.error("Find all range Computer like {} order by {} ", by,
					order);
			throw new DaoException();
		}
		return lcomputer;
	}

	public List<Computer> findAll(int offset, int range, String pattern,
			String by, String order) {
		List<Computer> lcomputer = new LinkedList<Computer>();

		try {
			lcomputer = this.jdbcTemplate
					.query("select computer.id as c_id, computer.name as c_name,introduced,discontinued,company_id,company.name "
							+ "from computer left outer join company "
							+ "on computer.company_id = company.id "
							+ "where computer.name like ? or company.name like ? "
							+ "order by "
							+ by
							+ " "
							+ order
							+ " "
							+ "limit ? offset ?", new Object[] {
							"%" + pattern + "%", "%" + pattern + "%", range,
							offset }, new ComputerMapper());
		} catch (DataAccessException e) {
			logger.error(
					"Find all range Computer like {} order by {} with pattern {} ",
					by, order, pattern);
			throw new DaoException();
		}
		return lcomputer;
	}

	public List<Computer> findAll(Company obj) {
		List<Computer> lcomputer = new LinkedList<Computer>();

		try {
			lcomputer = this.jdbcTemplate
					.query("select computer.id as c_id, computer.name as c_name,introduced,discontinued,company_id,company.name "
							+ "from computer left outer join company "
							+ "on computer.company_id = company.id "
							+ "where company.id = ?",
							new Object[] { obj.getId() }, new ComputerMapper());
		} catch (DataAccessException e) {
			logger.error("Find all Computer with a Company : {} ", obj);
			throw new DaoException();
		}
		return lcomputer;
	}
}
