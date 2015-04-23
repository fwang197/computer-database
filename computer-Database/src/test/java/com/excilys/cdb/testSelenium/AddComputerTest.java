package com.excilys.cdb.testSelenium;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.excilys.cdb.dao.DateMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.tools.Tools;

public class AddComputerTest {
	private static ServiceComputer comp;
	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		Tools.process();
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/computer-Database/DashboardServlet";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		comp = ServiceComputer.INSTANCE;
	}

	@Test
	public void testSelenium1() throws Exception {
		Tools.process();
		driver.get(baseUrl);

		driver.findElement(By.id("addComputer")).click();
		driver.findElement(By.id("computerName")).clear();
		driver.findElement(By.id("computerName")).sendKeys("test1");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

		Computer expected = new Computer(7, "test1", null, null, null);
		Computer actual = comp.findComputer(7);

		assertEquals(expected, actual);
	}

	@Test
	public void testSelenium2() throws Exception {
		Tools.process();
		driver.get(baseUrl);
		driver.findElement(By.id("addComputer")).click();
		driver.findElement(By.id("computerName")).clear();
		driver.findElement(By.id("computerName")).sendKeys("test2");
		new Select(driver.findElement(By.id("companyId")))
				.selectByVisibleText("Apple Inc.");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

		Computer expected = new Computer(7, "test2", null, null, new Company(1,
				"Apple Inc."));
		Computer actual = comp.findComputer(7);
		assertEquals(expected, actual);
	}

	@Test
	public void testSelenium3() throws Exception {
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

		Computer expected = new Computer(7, "test3",
				DateMapper.toDateFormat("2000-01-03"), null, new Company(1,
						"Apple Inc."));
		Computer actual = comp.findComputer(7);
		assertEquals(expected, actual);
	}

	@Test
	public void testSelenium4() throws Exception {
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

		Computer expected = new Computer(7, "test4",
				DateMapper.toDateFormat("2000-01-03"),
				DateMapper.toDateFormat("2001-01-03"), new Company(1,
						"Apple Inc."));
		Computer actual = comp.findComputer(7);
		assertEquals(expected, actual);
	}

	@Test
	public void testSelenium5() throws Exception {
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
