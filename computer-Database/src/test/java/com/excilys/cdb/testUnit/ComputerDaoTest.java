package com.excilys.cdb.testUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.dao.DateMapper;
import com.excilys.cdb.dao.IDao;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.tools.Tools;

public class ComputerDaoTest {
	private static IDao<Computer> comp;

	@BeforeClass
	public static void initComputerDao() {
		comp = ComputerDao.INSTANCE;
	}

	@After
	public void resetTestDB() {
		Tools.process();
	}

	@Test
	public void testFind() {
		Tools.process();
		Computer expected = new Computer.ComputerBuilder(
				"MacBook Pro 15.4 inch")
				.setId(1)
				.setCompany(
						new Company.CompanyBuilder("Apple Inc.").setId(1)
								.build()).build();
		Computer actual = comp.find(1);
		assertEquals(expected, actual);
	}

	@Test
	public void testCreate() {
		Tools.process();
		Computer expected = new Computer.ComputerBuilder("test").setId(7)
				.setDiscontinued(DateMapper.toDateFormat("2000-01-02")).build();
		long id = comp.create(expected);
		Computer actual = comp.find(id);
		assertEquals(expected, actual);
	}

	@Test
	public void testUpdate() {
		Tools.process();
		Computer expected = new Computer.ComputerBuilder("Powerbook 101")
				.setId(4).build();
		comp.update(expected);
		Computer actual = comp.find(4);
		assertEquals(expected, actual);
	}

	@Test
	public void testDelete() {
		Tools.process();
		Computer expected = new Computer.ComputerBuilder(
				"MacBook Pro 15.4 inch")
				.setId(1)
				.setCompany(
						new Company.CompanyBuilder("Apple Inc.").setId(1)
								.build()).build();
		comp.delete(expected);
		Computer actual = comp.find(1);
		assertNotEquals(expected, actual);
	}

	@Test
	public void testFindAll() {
		Tools.process();
		ArrayList<Computer> lexpected = new ArrayList<Computer>();
		lexpected.add(new Computer.ComputerBuilder("MacBook Pro 15.4 inch")
				.setId(1)
				.setCompany(
						new Company.CompanyBuilder("Apple Inc.").setId(1)
								.build()).build());
		lexpected.add(new Computer.ComputerBuilder("MacBook Pro")
				.setId(2)
				.setIntroduced(DateMapper.toDateFormat("2006-01-10"))
				.setCompany(
						new Company.CompanyBuilder("Apple Inc.").setId(1)
								.build()).build());
		lexpected.add(new Computer.ComputerBuilder("Apple III")
				.setId(3)
				.setIntroduced(DateMapper.toDateFormat("1980-05-01"))
				.setDiscontinued(DateMapper.toDateFormat("1984-04-01"))
				.setCompany(
						new Company.CompanyBuilder("Apple Inc.").setId(1)
								.build()).build());
		lexpected.add(new Computer.ComputerBuilder("Powerbook 100").setId(4)
				.build());
		lexpected.add(new Computer.ComputerBuilder("Manchester Mark I")
				.setId(5).setIntroduced(DateMapper.toDateFormat("1989-01-01"))
				.build());
		lexpected
				.add(new Computer.ComputerBuilder("Xerox Daybreak").setId(6)
						.setIntroduced(DateMapper.toDateFormat("1985-01-01"))
						.setDiscontinued(DateMapper.toDateFormat("1989-01-01"))
						.build());
		ArrayList<Computer> lactual = new ArrayList<Computer>(comp.findAll());
		assertTrue(lexpected.equals(lactual));
	}
}
