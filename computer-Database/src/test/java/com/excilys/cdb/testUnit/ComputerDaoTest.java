package test.java.com.excilys.cdb.testUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import main.java.com.excilys.cdb.dao.ComputerDao;
import main.java.com.excilys.cdb.dao.IComputerDao;
import main.java.com.excilys.cdb.mapper.Mapper;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.tools.Tools;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

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
				Mapper.toDateFormat("2000-01-02 00:00:00"), null);
		comp.create(expected);
		Computer actual = comp.find(7);
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
		lexpected.add(new Computer(2, "MacBook Pro", Mapper
				.toDateFormat("2006-01-10 00:00:00"), null, new Company(1,
				"Apple Inc.")));
		lexpected.add(new Computer(3, "Apple III", Mapper
				.toDateFormat("1980-05-01 00:00:00"), Mapper
				.toDateFormat("1984-04-01 00:00:00"), new Company(1,
				"Apple Inc.")));
		lexpected.add(new Computer(4, "Powerbook 100", null, null, null));
		lexpected.add(new Computer(5, "Manchester Mark I", Mapper
				.toDateFormat("1989-01-01 00:00:00"), null, null));
		lexpected.add(new Computer(6, "Xerox Daybreak", Mapper
				.toDateFormat("1985-01-01 00:00:00"), Mapper
				.toDateFormat("1989-01-01 00:00:00"), null));
		ArrayList<Computer> lactual = new ArrayList<Computer>(comp.findAll());
		assertTrue(lexpected.equals(lactual));
	}

}
