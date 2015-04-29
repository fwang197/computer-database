package com.excilys.cdb.testUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.dao.IDao;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class ComputerDaoFindTest {
	private static IDao<Computer> comp;

	@BeforeClass
	public static void initCompanyDao() {
		comp = ComputerDao.INSTANCE;
	}

	@Test
	public void testValid() {
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
	public void testNotValid() {
		Computer actual = comp.find(7);
		assertNull(actual);
	}

	@Test
	public void testNotValid2() {
		Computer actual = comp.find(-1);
		assertNull(actual);
	}
}