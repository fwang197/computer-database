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
import com.excilys.cdb.dao.IComputerDao;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.tools.Tools;

public class ComputerDaoTest {
	private static IComputerDao comp;

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
		Computer expected = new Computer(1, "MacBook Pro 15.4 inch", null,
				null, new Company(1, "Apple Inc."));
		Computer actual = comp.find(1);
		assertEquals(expected, actual);
	}

	@Test
	public void testCreate() {
		Tools.process();
		Computer expected = new Computer(7, "test", null,
				DateMapper.toDateFormat("2000-01-02"), null);
		long id = comp.create(expected);
		Computer actual = comp.find(id);
		assertEquals(expected, actual);
	}

	@Test
	public void testUpdate() {
		Tools.process();
		Computer expected = new Computer(4, "Powerbook 101", null, null, null);
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
		lexpected
				.add(new Computer(2, "MacBook Pro", DateMapper
						.toDateFormat("2006-01-10"), null, new Company(1,
						"Apple Inc.")));
		lexpected.add(new Computer(3, "Apple III", DateMapper
				.toDateFormat("1980-05-01"), DateMapper
				.toDateFormat("1984-04-01"), new Company(1, "Apple Inc.")));
		lexpected.add(new Computer(4, "Powerbook 100", null, null, null));
		lexpected.add(new Computer(5, "Manchester Mark I", DateMapper
				.toDateFormat("1989-01-01"), null, null));
		lexpected.add(new Computer(6, "Xerox Daybreak", DateMapper
				.toDateFormat("1985-01-01"), DateMapper
				.toDateFormat("1989-01-01"), null));
		ArrayList<Computer> lactual = new ArrayList<Computer>(comp.findAll());
		assertTrue(lexpected.equals(lactual));
	}

}
