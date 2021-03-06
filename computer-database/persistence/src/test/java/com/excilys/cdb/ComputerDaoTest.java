package com.excilys.cdb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.dao.IDao;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.tools.DateMapper;
import com.excilys.cdb.tools.Tools;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/persistence-context-test.xml" })
@Transactional
public class ComputerDaoTest {

	@Autowired
	private IDao<Computer> comp;

	@BeforeClass
	public static void initComputerDao() {
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
				.setDiscontinued(DateMapper.toDate("2000", "01", "02")).build();
		comp.create(expected);
		Computer actual = comp.find(expected.getId());
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
				.setIntroduced(DateMapper.toDate("2006", "01", "10"))
				.setCompany(
						new Company.CompanyBuilder("Apple Inc.").setId(1)
								.build()).build());
		lexpected.add(new Computer.ComputerBuilder("Apple III")
				.setId(3)
				.setIntroduced(DateMapper.toDate("1980", "05", "01"))
				.setDiscontinued(DateMapper.toDate("1984", "04", "01"))
				.setCompany(
						new Company.CompanyBuilder("Apple Inc.").setId(1)
								.build()).build());
		lexpected.add(new Computer.ComputerBuilder("Powerbook 100").setId(4)
				.build());
		lexpected.add(new Computer.ComputerBuilder("Manchester Mark I")
				.setId(5).setIntroduced(DateMapper.toDate("1989", "01", "01"))
				.build());
		lexpected
				.add(new Computer.ComputerBuilder("Xerox Daybreak").setId(6)
						.setIntroduced(DateMapper.toDate("1985", "01", "01"))
						.setDiscontinued(DateMapper.toDate("1989", "01", "01"))
						.build());
		ArrayList<Computer> lactual = new ArrayList<Computer>(comp.findAll());
		assertTrue(lexpected.equals(lactual));
	}
}
