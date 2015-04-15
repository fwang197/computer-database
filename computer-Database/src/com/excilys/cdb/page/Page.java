package com.excilys.cdb.page;

import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * La classe permet d'effectuer la pagination.
 *
 * @author excilys
 * @param <T>
 *            the generic type
 */
public class Page<T> {
	// Le offsetseur de la position courante
	/** The offset. */
	private int offset = 0;
	// la nombre d'élements à afficher
	/** The range. */
	private int range;

	/**
	 * Instantiates a new page.
	 * 
	 * @param range
	 *            the range
	 */
	public Page(String table, int range) {
		this.range = range;
	}

	/**
	 * Permet d'afficher les range éléments avant la position courante.
	 * 
	 */
	private void backward() {

	}

	/**
	 * Permet d'afficher les range éléments apres la position courante.
	 */
	private void forward() {

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
