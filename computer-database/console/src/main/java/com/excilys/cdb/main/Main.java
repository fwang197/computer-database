package com.excilys.cdb.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.cdb.service.IServiceCompany;
import com.excilys.cdb.service.IServiceComputer;
import com.excilys.cdb.ui.Menu;

/**
 * The Class Main.
 */
public class Main {

	public static IServiceCompany servicecompany;
	public static IServiceComputer servicecomputer;

	static {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"/applicationContext.xml");
		servicecompany = context.getBean(IServiceCompany.class);
		servicecomputer = context.getBean(IServiceComputer.class);
		((AbstractApplicationContext) context).close();
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.loop();
	}
}
