package com.excilys.cdb.testUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.cdb.dao.IDao;
import com.excilys.cdb.model.Company;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContextTest.xml" })
public class CompanyDaoTest {

	@Autowired
	private IDao<Company> comp;

	@BeforeClass
	public static void initCompanyDao() {

	}

	@Test
	public void testFindValid() {
		Company expected = new Company.CompanyBuilder("Apple Inc.").setId(1)
				.build();
		Company actual = comp.find(1);
		assertEquals(expected, actual);
	}

	@Test
	public void testFindAllValid() {
		ArrayList<Company> lexpected = new ArrayList<Company>();
		lexpected
				.add(new Company.CompanyBuilder("Apple Inc.").setId(1).build());
		lexpected.add(new Company.CompanyBuilder("Thinking Machines").setId(2)
				.build());
		lexpected.add(new Company.CompanyBuilder("RCA").setId(3).build());
		ArrayList<Company> lactual = new ArrayList<Company>(comp.findAll());
		assertTrue(lexpected.equals(lactual));

	}
}
