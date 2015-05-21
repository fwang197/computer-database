package com.excilys.cdb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.dao.IDao;
import com.excilys.cdb.model.Company;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/persistence-context-test.xml" })
@Transactional
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
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testFindAllValid() {
		List<Company> lexpected = new ArrayList<Company>();
		lexpected
				.add(new Company.CompanyBuilder("Apple Inc.").setId(1).build());
		lexpected.add(new Company.CompanyBuilder("Thinking Machines").setId(2)
				.build());
		lexpected.add(new Company.CompanyBuilder("RCA").setId(3).build());
		List<Company> lactual = new ArrayList<Company>(comp.findAll());
		System.out.println("EXPEC " + lexpected);
		System.out.println("ACTUA " + lactual);
		assertTrue(lexpected.equals(lactual));

	}
}
