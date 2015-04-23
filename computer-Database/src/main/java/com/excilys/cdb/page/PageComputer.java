package com.excilys.cdb.page;

import java.util.ArrayList;
import java.util.Scanner;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ServiceComputer;

// TODO: Auto-generated Javadoc
/**
 * La classe permet d'effectuer la pagination.
 *
 * @author excilys
 * @param <T>
 *            the generic type
 */
public class PageComputer {
	/** The offset. */
	private int offset = 0;
	/** The range. */
	private int range;

	private String table;

	/**
	 * Instantiates a new page.
	 * 
	 * @param range
	 *            the range
	 */
	public PageComputer(String table, int range) {
		this.table = table;
		this.range = range;
	}

	/**
	 * Permet d'afficher les range éléments avant la position courante.
	 * 
	 */
	private void forward() {
		ArrayList<Computer> l = new ArrayList<Computer>(
				ServiceComputer.INSTANCE.findAllComputer(offset + range, range,
						"computer.id", ""));
		for (Computer c : l)
			System.out.println(c);
		if (l.size() != range) {
			offset += l.size();
		} else {
			offset += range;
		}
	}

	/**
	 * Permet d'afficher les range éléments apres la position courante.
	 */
	private void backward() {
		int tmp = offset - 2 * range;
		if (tmp < 0) {
			tmp = 0;
		}
		ArrayList<Computer> l = new ArrayList<Computer>(
				ServiceComputer.INSTANCE.findAllComputer(tmp, range,
						"computer.id", ""));
		for (Computer c : l)
			System.out.println(c);
		offset = tmp;
		offset += range;
	}

	/**
	 * La méthode qui s'occupe de la pagination.
	 */
	public void navigation() {
		Scanner sc = new Scanner(System.in);
		String res = "";
		while (!res.equals("q")) {
			System.out.println("<- (-)		exit(q)		-> (+)");
			System.out.println("> ");
			System.out.println(offset);
			res = sc.nextLine();
			switch (res) {
			case "q":
				break;
			case "-":
				backward();
				break;
			case "+":
				forward();
				break;
			default:
				System.err.println("Incorrect input!");
				break;
			}
		}
	}
}
