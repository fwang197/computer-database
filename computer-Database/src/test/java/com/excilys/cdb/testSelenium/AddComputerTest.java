package com.excilys.cdb.testSelenium;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.cdb.mapper.DateMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.tools.Tools;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContextTest.xml" })
public class AddComputerTest {

	@Autowired
	private ServiceComputer comp;

	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		Tools.process();
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/computer-database/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void testSeleniumAddComputerValid() throws Exception {
		Tools.process();
		driver.get(baseUrl);

		driver.findElement(By.id("addComputer")).click();
		driver.findElement(By.id("computerName")).clear();
		driver.findElement(By.id("computerName")).sendKeys("test1");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

		Computer expected = new Computer.ComputerBuilder("test1").setId(7)
				.build();
		Computer actual = comp.findComputer(7);

		assertEquals(expected, actual);
	}

	@Test
	public void testSeleniumAddComputerCompanyValid() throws Exception {
		Tools.process();
		driver.get(baseUrl);
		driver.findElement(By.id("addComputer")).click();
		driver.findElement(By.id("computerName")).clear();
		driver.findElement(By.id("computerName")).sendKeys("test2");
		new Select(driver.findElement(By.id("companyId")))
				.selectByVisibleText("Apple Inc.");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

		Computer expected = new Computer.ComputerBuilder("test2")
				.setId(7)
				.setCompany(
						new Company.CompanyBuilder("Apple Inc.").setId(1)
								.build()).build();
		Computer actual = comp.findComputer(7);
		assertEquals(expected, actual);
	}

	@Test
	public void testSeleniumAddComputerCompanyIntroValid() throws Exception {
		Tools.process();
		driver.get(baseUrl);
		driver.findElement(By.id("addComputer")).click();
		driver.findElement(By.id("computerName")).clear();
		driver.findElement(By.id("computerName")).sendKeys("test3");
		driver.findElement(By.id("introduced")).clear();
		driver.findElement(By.id("introduced")).sendKeys("2000-01-03");
		new Select(driver.findElement(By.id("companyId")))
				.selectByVisibleText("Apple Inc.");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

		Computer expected = new Computer.ComputerBuilder("test3")
				.setId(7)
				.setIntroduced(DateMapper.toDateFormat("2000-01-03"))
				.setCompany(
						new Company.CompanyBuilder("Apple Inc.").setId(1)
								.build()).build();
		Computer actual = comp.findComputer(7);
		assertEquals(expected, actual);
	}

	@Test
	public void testSeleniumAddComputerCompanyIntroDisconValid()
			throws Exception {
		Tools.process();
		driver.get(baseUrl);
		driver.findElement(By.id("addComputer")).click();
		driver.findElement(By.id("computerName")).clear();
		driver.findElement(By.id("computerName")).sendKeys("test4");
		driver.findElement(By.id("introduced")).clear();
		driver.findElement(By.id("introduced")).sendKeys("2000-01-03");
		driver.findElement(By.id("discontinued")).clear();
		driver.findElement(By.id("discontinued")).sendKeys("2001-01-03");
		new Select(driver.findElement(By.id("companyId")))
				.selectByVisibleText("Apple Inc.");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

		Computer expected = new Computer.ComputerBuilder("test4")
				.setId(7)
				.setIntroduced(DateMapper.toDateFormat("2000-01-03"))
				.setDiscontinued(DateMapper.toDateFormat("2001-01-03"))
				.setCompany(
						new Company.CompanyBuilder("Apple Inc.").setId(1)
								.build()).build();

		Computer actual = comp.findComputer(7);
		assertEquals(expected, actual);
	}

	@Test
	public void testSeleniumAddComputerFail() throws Exception {
		Tools.process();
		driver.get(baseUrl);
		driver.findElement(By.id("addComputer")).click();
		driver.findElement(By.id("computerName")).clear();
		driver.findElement(By.id("computerName")).sendKeys("");
		new Select(driver.findElement(By.id("companyId")))
				.selectByVisibleText("Apple Inc.");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		ArrayList<Computer> l = new ArrayList<Computer>(comp.findAllComputer());
		assertEquals(6, l.size());
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
