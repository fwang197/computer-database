package com.excilys.cdb.testUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.IServiceComputer;
import com.excilys.cdb.service.ServiceComputer;

public class ServiceComputerFindTest {

	private static IServiceComputer serv;

	@BeforeClass
	public static void initCompanyDao() {
		serv = ServiceComputer.INSTANCE;
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
