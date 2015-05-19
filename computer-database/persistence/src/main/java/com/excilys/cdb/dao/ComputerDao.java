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

		try {
			res = (long) this.sessionFactory.getCurrentSession().save(comp);
		} catch (DataAccessException e) {
			logger.error("Create Computer error : {} ", comp);
			throw new DaoException();
		}
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
					.createQuery("delete from Computer where company = ?")
					.setParameter(0, comp.getId()).executeUpdate();
		} catch (DataAccessException e) {
			logger.error("Delete Computer error : {} ", comp);
			throw new DaoException();
		}
	}

	public void delete(Computer comp) {
		try {
			this.sessionFactory.getCurrentSession()
					.createQuery("delete from Computer where id = ?")
					.setParameter(0, comp.getId()).executeUpdate();
		} catch (DataAccessException e) {
			logger.error("Delete Computer error : {} ", comp);
			throw new DaoException();
		}
	}

	public long getCount() {
		long res = 0;
		try {
			res = (long) this.sessionFactory.getCurrentSession()
					.createQuery("select count(c) from Computer as c")
					.uniqueResult();
		} catch (DataAccessException e) {
			logger.error("Get all Computer count error");
			throw new DaoException();
		}
		return res;
	}

	public long getCount(String pattern) {
		long res = 0;
		try {
			res = (long) this.sessionFactory
					.getCurrentSession()
					.createQuery(
							"select count(c) from Computer as c left outer join c.company c2 where c.name like ? or c2.name like ?")
					.setParameter(0, "%" + pattern + "%")
					.setParameter(1, "%" + pattern + "%").uniqueResult();

		} catch (DataAccessException e) {
			logger.error("Get all Computer with pattern {} error", pattern);
			throw new DaoException();
		}
		return res;
	}

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	public List<Computer> findAll(int offset, int range, Row by, Order order) {
		List<Computer> lcomputer = new LinkedList<Computer>();
		try {

			lcomputer = this.sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Computer as computer order by " + by + " "
									+ order).setFirstResult(offset)
					.setMaxResults(range).list();
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.error("Find all range Computer  by {} order {}", by, order);
			throw new DaoException();
		}
		return lcomputer;
	}

	@SuppressWarnings("unchecked")
	public List<Computer> findAll(int offset, int range, String pattern,
			Row by, Order order) {
		List<Computer> lcomputer = new LinkedList<Computer>();

		try {

			lcomputer = this.sessionFactory
					.getCurrentSession()
					.createQuery(
							"select computer from Computer as computer left outer join computer.company c2 where computer.name like ? or c2.name like ? order by "
									+ by + " " + order)
					.setParameter(0, "%" + pattern + "%")
					.setParameter(1, "%" + pattern + "%").setMaxResults(range)
					.list();
		} catch (DataAccessException e) {
			logger.error(
					"Find all range Computer like {} order by {} with pattern {} ",
					by, order, pattern);
			throw new DaoException();
		}
		return lcomputer;
	}

	@SuppressWarnings("unchecked")
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
		NAME("computer.name"), INTRO("computer.introduced"), DISCON(
				"computer.discontinued"), COMPANY("computer.company.name"), ID(
				"computer.id");

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
