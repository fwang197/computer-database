package com.excilys.cdb.testSelenium;

import static org.junit.Assert.assertEquals;

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

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.tools.Tools;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class EditComputerTest {

	@Autowired
	private ServiceComputer comp;
	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		Tools.process();
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/computer-Database/DashboardServlet";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testSelenium1() throws Exception {
		Tools.process();
		driver.get(baseUrl);

		driver.findElement(By.linkText("MacBook Pro 15.4 inch")).click();
		new Select(driver.findElement(By.id("companyId")))
				.selectByVisibleText("--");
		driver.findElement(By.id("computerName")).clear();
		driver.findElement(By.id("computerName")).sendKeys("test");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

		Computer expected = new Computer.ComputerBuilder("test").setId(1)
				.build();
		Computer actual = comp.findComputer(1);

		assertEquals(expected, actual);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
