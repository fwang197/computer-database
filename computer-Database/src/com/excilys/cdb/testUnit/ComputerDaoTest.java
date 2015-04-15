package com.excilys.cdb.testUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.dao.IComputerDao;
import com.excilys.cdb.mapper.Mapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.tools.Tools;

public class ComputerDaoTest {
	private static IComputerDao comp;

	@BeforeClass
	public static void initComputerDao() {
		comp = ComputerDao.INSTANCE;
	}

	@Test
	public void testFind() {
		Computer expected = new Computer(1, "MacBook Pro 15.4 inch", null,
				null, new Company(1, "Apple Inc."));
		Computer actual = comp.find(1);
		assertEquals(expected, actual);
	}

	@Test
	public void testCreate() {
		Tools.process();
		Computer expected = new Computer(7, "test", null,
				Mapper.toLocalDateTime(java.sql.Timestamp
						.valueOf("2000-01-02 00:00:00")), null);
		comp.create(expected);
		Computer actual = comp.find(7);
		assertEquals(expected, actual);
	}

	@Test
	public void testUpdate() {
		// Tools.process();
		Computer expected = new Computer(4, "Powerbook 101", null, null,
				new Company());
		comp.update(expected);
		Computer actual = comp.find(4);
		assertEquals(expected, actual);
	}

	@Test
	public void testDelete() {
		Tools.process();
		Computer expected = new Computer(1, "MacBook Pro 15.4 inch", null,
				null, new Company(1, "Apple Inc."));
		comp.delete(expected);
		Computer actual = comp.find(1);
		assertNotEquals(expected, actual);
	}

	@Test
	public void testFindAll() {
		Tools.process();
		ArrayList<Computer> lexpected = new ArrayList<Computer>();
		lexpected.add(new Computer(1, "MacBook Pro 15.4 inch", null, null,
				new Company(1, "Apple Inc.")));
		lexpected.add(new Computer(2, "MacBook Pro", Mapper
				.toLocalDateTime(java.sql.Timestamp
						.valueOf("2006-01-10 00:00:00")), null, new Company(1,
				"Apple Inc.")));
		lexpected.add(new Computer(3, "Apple III", Mapper
				.toLocalDateTime(java.sql.Timestamp
						.valueOf("1980-05-01 00:00:00")), Mapper
				.toLocalDateTime(java.sql.Timestamp
						.valueOf("1984-04-01 00:00:00")), new Company(1,
				"Apple Inc.")));
		lexpected.add(new Computer(4, "Powerbook 100", null, null,
				new Company()));
		lexpected.add(new Computer(5, "Manchester Mark I", Mapper
				.toLocalDateTime(java.sql.Timestamp
						.valueOf("1989-01-01 00:00:00")), null, new Company()));
		lexpected.add(new Computer(6, "Xerox Daybreak", Mapper
				.toLocalDateTime(java.sql.Timestamp
						.valueOf("1985-01-01 00:00:00")), Mapper
				.toLocalDateTime(java.sql.Timestamp
						.valueOf("1989-01-01 00:00:00")), new Company()));
		ArrayList<Computer> lactual = new ArrayList<Computer>(comp.findAll());
		assertTrue(lexpected.equals(lactual));
	}
}
