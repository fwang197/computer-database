package com.excilys.cdb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.IServiceComputer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContextTest.xml" })
public class ServiceComputerFindTest {

	@Autowired
	private IServiceComputer serv;

	@BeforeClass
	public static void initCompanyDao() {
	}

	@Test
	public void testValid() {
		Computer expected = Mockito.mock(Computer.class);

		Mockito.when(expected.getId()).thenReturn(1L);

		Computer actual = serv.findComputer(1);
		assertEquals(expected.getId(), actual.getId());
	}

	@Test
	public void testNotValid() {
		Computer actual = serv.findComputer(7);
		assertNull(actual);
	}

	@Test
	public void testNotValid2() {
		Computer actual = serv.findComputer(-1);
		assertNull(actual);
	}

}
