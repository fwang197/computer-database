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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.cdb.tools.Tools;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TotalCountTest {

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

		driver.findElement(By.linkText("Application - Computer Database"))
				.click();

		int expected = Integer.parseInt(driver.findElement(By.id("homeTitle"))
				.getText().split(" ")[0]);
		int actual = 6;

		assertEquals(expected, actual);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
