package com.excilys.cdb.testUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import com.excilys.cdb.model.Company;

import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.cdb.dao.CompanyDao;
import com.excilys.cdb.dao.ICompanyDao;

public class CompanyDaoTest {
	private static ICompanyDao comp;

	@BeforeClass
	public static void initCompanyDao() {
		comp = CompanyDao.INSTANCE;
	}

	@Test
	public void testFind() {
		Company expected = new Company(1, "Apple Inc.");
		Company actual = comp.find(1);
		assertEquals(expected, actual);
	}

	@Test
	public void testFindAll() {
		ArrayList<Company> lexpected = new ArrayList<Company>();
		lexpected.add(new Company(1, "Apple Inc."));
		lexpected.add(new Company(2, "Thinking Machines"));
		lexpected.add(new Company(3, "RCA"));
		ArrayList<Company> lactual = new ArrayList<Company>(comp.findAll());
		assertTrue(lexpected.equals(lactual));

	}
}
