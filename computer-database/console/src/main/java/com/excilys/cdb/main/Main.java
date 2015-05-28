package com.excilys.cdb.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.cdb.clientService.IClientCompanyService;
import com.excilys.cdb.clientService.IClientComputerService;
import com.excilys.cdb.ui.Menu;

/**
 * The Class Main.
 */
public class Main {

	public static IClientCompanyService companyService;
	public static IClientComputerService computerService;

	static {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"/console-context.xml");
		companyService = context.getBean(IClientCompanyService.class);
		computerService = context.getBean(IClientComputerService.class);
		((AbstractApplicationContext) context).close();
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) throws Exception {
		Menu menu = new Menu();
		menu.loop();
	}
}
