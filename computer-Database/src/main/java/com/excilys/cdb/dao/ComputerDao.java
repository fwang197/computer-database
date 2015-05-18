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
import com.excilys.cdb.model.Computer;

@Repository("computerDao")
public class ComputerDao implements IDao<Computer> {

	@Autowired
	private SessionFactory sessionFactory;

	private final Logger logger = LoggerFactory.getLogger(ComputerDao.class);

	public ComputerDao() {
	}

	public void create(Computer comp) {
		long res = -1;
		// PreparedStatementCreator preparestatementcreator = new
		// PreparedStatementCreator() {
		//
		// public PreparedStatement createPreparedStatement(Connection conn)
		// throws SQLException {
		// PreparedStatement prepare = conn
		// .prepareStatement(
		// "insert into computer(name,introduced,discontinued,company_id) values (?,?,?,?)",
		// Statement.RETURN_GENERATED_KEYS);
		// prepare.setString(1, comp.getName());
		// prepare.setTimestamp(2,
		// DateMapper.toTimeStamp(comp.getIntroduced()));
		// prepare.setTimestamp(3,
		// DateMapper.toTimeStamp(comp.getDiscontinued()));
		// if (Tools.isNull(comp.getCompany())) {
		// prepare.setNull(4, Types.NULL);
		// } else {
		// prepare.setLong(4, comp.getCompany().getId());
		// }
		// return prepare;
		// }
		// };
		//
		// try {
		// KeyHolder holder = new GeneratedKeyHolder();
		// this.jdbcTemplate.update(preparestatementcreator, holder);
		// res = holder.getKey().longValue();
		// } catch (DataAccessException e) {
		// logger.error("Create Computer error : {} ", comp);
		// throw new DaoException();
		// }
		comp.setId(res);
	}

	public Computer find(long id) {

		Computer computer = null;
		try {
			computer = (Computer) this.sessionFactory.getCurrentSession()
					.createQuery("from Computer as c where c.id = ?")
					.setParameter(0, id).uniqueResult();
		} catch (DataAccessException e) {
			logger.error("Find Computer error : {} " + e.getMessage());
		}
		return computer;
	}

	public void update(Computer comp) {
		try {
			this.sessionFactory
					.getCurrentSession()
					.createQuery(
							"update Computer set name = ?, introduced = ? , discontinued = ? , company_id = ? where id = ?")
					.setParameter(0, comp.getName())
					.setParameter(1, comp.getIntroduced())
					.setParameter(2, comp.getDiscontinued())
					.setParameter(3, comp.getCompany().getId())
					.setParameter(4, comp.getId()).executeUpdate();
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.error("Update Computer error : {} ", comp);
			throw new DaoException();
		}
	}

	public void delete(Company comp) {
		try {
			this.sessionFactory.getCurrentSession()
					.createQuery("delete from computer where company_id = ?")
					.setParameter(0, comp.getId());
		} catch (DataAccessException e) {
			logger.error("Delete Computer error : {} ", comp);
			throw new DaoException();
		}
	}

	public void delete(Computer comp) {
		try {
			this.sessionFactory.getCurrentSession()
					.createQuery("delete from Computer where id = ?")
					.setParameter(0, comp.getId());
		} catch (DataAccessException e) {
			logger.error("Delete Computer error : {} ", comp);
			throw new DaoException();
		}
	}

	public int getCount() {
		int res = 0;
		try {
			res = (int) this.sessionFactory.getCurrentSession()
					.createQuery("select count(c) from Computer as c")
					.uniqueResult();
		} catch (DataAccessException e) {
			logger.error("Get all Computer count error");
			throw new DaoException();
		}
		return res;
	}

	public int getCount(String pattern) {
		int res = 0;
		try {
			res = (int) this.sessionFactory
					.getCurrentSession()
					.createQuery(
							"select count(c) from Computer as c where c.name like ? or c.company.name like ? ")
					.setParameter(0, "%" + pattern + "%")
					.setParameter(1, "%" + pattern + "%").uniqueResult();
		} catch (DataAccessException e) {
			logger.error("Get all Computer with pattern {} error", pattern);
			throw new DaoException();
		}
		return res;
	}

	public List<Computer> findAll() {

		List<Computer> lcomputer = new LinkedList<Computer>();

		try {

			lcomputer = this.sessionFactory.getCurrentSession()
					.createQuery("from Computer ").list();
		} catch (DataAccessException e) {
			logger.error("Find all Computer error");
			throw new DaoException();
		}
		return lcomputer;
	}

	public List<Computer> findAll(int offset, int range, Row by, Order order) {
		List<Computer> lcomputer = new LinkedList<Computer>();
		try {

			lcomputer = this.sessionFactory.getCurrentSession()
					.createQuery("from Computer order by ? ?")
					.setParameter(0, by).setParameter(1, order)
					.setFirstResult(offset).setMaxResults(range).list();
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.error("Find all range Computer  by {} order {}", by, order);
			throw new DaoException();
		}
		return lcomputer;
	}

	public List<Computer> findAll(int offset, int range, String pattern,
			Row by, Order order) {
		List<Computer> lcomputer = new LinkedList<Computer>();

		try {

			lcomputer = this.sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Computer as c order by ? ? where c.name like ? or c.company.name like ?")
					.setParameter(0, by).setParameter(1, order)
					.setParameter(2, "%" + pattern + "%")
					.setParameter(3, "%" + pattern + "%")
					.setFirstResult(offset).setMaxResults(range).list();
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

			lcomputer = this.sessionFactory.getCurrentSession()
					.createQuery("from Computer as c where c.company.id = ?")
					.setParameter(0, obj.getId()).list();
		} catch (DataAccessException e) {
			logger.error("Find all Computer with a Company : {} ", obj);
			throw new DaoException();
		}
		return lcomputer;
	}

	public enum Order {
		ASC("asc"), DESC("desc");

		private String name = "";

		Order(String name) {
			this.name = name;
		}

		public String toString() {
			return name;
		}

		public static Order getOrder(String order) {
			if (order.equals(ASC.toString())) {
				return ASC;
			} else if (order.equals(DESC.toString())) {
				return DESC;
			}
			return ASC;
		}
	}

	public enum Row {
		NAME("computer.name"), INTRO("introduced"), DISCON("discontinued"), COMPANY(
				"company.name"), ID("computer.id");

		private String name = "";

		Row(String name) {
			this.name = name;
		}

		public String toString() {
			return name;
		}

		public static Row getRow(String row) {
			if (row.equals(NAME.toString())) {
				return NAME;
			} else if (row.equals(INTRO.toString())) {
				return INTRO;
			} else if (row.equals(DISCON.toString())) {
				return DISCON;
			} else if (row.equals(COMPANY.toString())) {
				return COMPANY;
			} else if (row.equals(ID.toString())) {
				return ID;
			}
			return ID;
		}
	}
}
