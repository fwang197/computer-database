package com.excilys.cdb.testSelenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.tools.Tools;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContextTest.xml" })
public class DeleteComputerTest {

	@Autowired
	private ServiceComputer comp;

	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;

	@Before
	public void setUp() throws Exception {
		Tools.process();
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/computer-database/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void testSeleniumDeleteComputerValid() throws Exception {
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
		Computer actual = comp.findComputer(1);
		assertEquals(expected, actual);
	}

	@Test
	public void testSeleniuDeleteAllComputerValid() throws Exception {
		Tools.process();
		driver.get(baseUrl);

		driver.findElement(By.id("editComputer")).click();
		driver.findElement(By.id("selectall")).click();
		driver.findElement(By.xpath("//a[@id='deleteSelected']/i")).click();
		assertTrue(closeAlertAndGetItsText()
				.matches(
						"^Are you sure you want to delete the selected computers[\\s\\S]$"));

		Computer expected = null;

		assertEquals(expected, comp.findComputer(1));
	}

	@Test
	public void testSeleniumDeleteComputerFail() throws Exception {
		Tools.process();
		driver.get(baseUrl);
		driver.findElement(By.id("editComputer")).click();
		driver.findElement(By.name("cb")).click();
		acceptNextAlert = false;
		driver.findElement(By.xpath("//a[@id='deleteSelected']/i")).click();
		assertTrue(closeAlertAndGetItsText()
				.matches(
						"^Are you sure you want to delete the selected computers[\\s\\S]$"));
		Computer expected = new Computer.ComputerBuilder(
				"MacBook Pro 15.4 inch")
				.setId(1)
				.setCompany(
						new Company.CompanyBuilder("Apple Inc.").setId(1)
								.build()).build();

		Computer actual = comp.findComputer(1);
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
