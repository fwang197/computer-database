package com.excilys.cdb.testSelenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.dao.IComputerDao;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.tools.Tools;

public class DeleteComputerTest {
	private static IComputerDao comp;
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;

	@Before
	public void setUp() throws Exception {
		Tools.process();
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/computer-Database/DashboardServlet";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		comp = ComputerDao.INSTANCE;
	}

	@Test
	public void testSelenium1() throws Exception {
		Tools.process();
		driver.get(baseUrl);
		;
		driver.findElement(By.id("editComputer")).click();
		driver.findElement(By.name("cb")).click();
		driver.findElement(By.xpath("//a[@id='deleteSelected']/i")).click();
		assertTrue(closeAlertAndGetItsText()
				.matches(
						"^Are you sure you want to delete the selected computers[\\s\\S]$"));

		Computer expected = null;
		Computer actual = comp.find(1);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testSelenium2() throws Exception {
		Tools.process();
		driver.get(baseUrl);

		driver.findElement(By.id("editComputer")).click();
		driver.findElement(By.id("selectall")).click();
		driver.findElement(By.xpath("//a[@id='deleteSelected']/i")).click();
		assertTrue(closeAlertAndGetItsText()
				.matches(
						"^Are you sure you want to delete the selected computers[\\s\\S]$"));

		Computer expected = null;

		assertEquals(expected, comp.find(1));
	}

	@Test
	public void testSelenium3() throws Exception {
		Tools.process();
		driver.get(baseUrl);
		driver.findElement(By.id("editComputer")).click();
		driver.findElement(By.name("cb")).click();
		acceptNextAlert = false;
		driver.findElement(By.xpath("//a[@id='deleteSelected']/i")).click();
		assertTrue(closeAlertAndGetItsText()
				.matches(
						"^Are you sure you want to delete the selected computers[\\s\\S]$"));
		Computer expected = new Computer(1, "MacBook Pro 15.4 inch", null,
				null, new Company(1, "Apple Inc."));
		Computer actual = comp.find(1);
		assertEquals(expected, actual);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
