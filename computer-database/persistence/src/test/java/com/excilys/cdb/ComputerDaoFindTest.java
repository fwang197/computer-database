package com.excilys.cdb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/persistence-context-test.xml" })
@Transactional
public class ComputerDaoFindTest {

	@Autowired
	private IDao<Computer> comp;

	@BeforeClass
	public static void initCompanyDao() {
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
	public void testNotValidOutRange() {
		Computer actual = comp.find(7);
		assertNull(actual);
	}

	@Test
	public void testNotValidValueNeg() {
		Computer actual = comp.find(-1);
		assertNull(actual);
	}
}