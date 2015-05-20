package com.excilys.cdb.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.tools.Tools;

@Repository("computerDao")
public class ComputerDao implements IDao<Computer> {

	@Autowired
	private SessionFactory sessionFactory;

	public ComputerDao() {
	}

	public void create(Computer comp) {
		long res = -1;
		res = (long) this.sessionFactory.getCurrentSession().save(comp);
		comp.setId(res);
	}

	public Computer find(long id) {

		return (Computer) this.sessionFactory.getCurrentSession()
				.createQuery("from Computer as c where c.id = ?")
				.setParameter(0, id).uniqueResult();
	}

	public void update(Computer comp) {

		this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"update Computer set name = ?, introduced = ? , discontinued = ? , company_id = ? where id = ?")
				.setParameter(0, comp.getName())
				.setParameter(1, comp.getIntroduced())
				.setParameter(2, comp.getDiscontinued())
				.setParameter(
						3,
						Tools.isNull(comp.getCompany()) ? null : comp
								.getCompany().getId())
				.setParameter(4, comp.getId()).executeUpdate();
	}

	public void delete(Company comp) {

		this.sessionFactory.getCurrentSession()
				.createQuery("delete from Computer where company = ?")
				.setParameter(0, comp.getId()).executeUpdate();

	}

	public void delete(Computer comp) {

		this.sessionFactory.getCurrentSession()
				.createQuery("delete from Computer where id = ?")
				.setParameter(0, comp.getId()).executeUpdate();

	}

	public long getCount() {
		return (long) this.sessionFactory.getCurrentSession()
				.createQuery("select count(c) from Computer as c")
				.uniqueResult();

	}

	public long getCount(String pattern) {
		return (long) this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"select count(c) from Computer as c left outer join c.company c2 where c.name like ? or c2.name like ?")
				.setParameter(0, "%" + pattern + "%")
				.setParameter(1, "%" + pattern + "%").uniqueResult();

	}

	@SuppressWarnings("unchecked")
	public List<Computer> findAll() {
		System.out.println(sessionFactory.toString());
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Computer ").list();

	}

	@SuppressWarnings("unchecked")
	public List<Computer> findAll(int offset, int range, Row by, Order order) {
		return this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Computer as computer order by " + by + " "
								+ order).setFirstResult(offset)
				.setMaxResults(range).list();
	}

	@SuppressWarnings("unchecked")
	public List<Computer> findAll(int offset, int range, String pattern,
			Row by, Order order) {
		return this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"select computer from Computer as computer left outer join computer.company c2 where computer.name like ? or c2.name like ? order by "
								+ by + " " + order)
				.setParameter(0, "%" + pattern + "%")
				.setParameter(1, "%" + pattern + "%").setMaxResults(range)
				.list();

	}

	@SuppressWarnings("unchecked")
	public List<Computer> findAll(Company obj) {
		return this.sessionFactory.getCurrentSession()
				.createQuery("from Computer as c where c.company.id = ?")
				.setParameter(0, obj.getId()).list();

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
