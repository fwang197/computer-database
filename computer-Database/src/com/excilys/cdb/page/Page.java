package com.excilys.cdb.page;

import java.util.LinkedList;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * La classe permet d'effectuer la pagination.
 *
 * @author excilys
 * @param <T> the generic type
 */
public class Page<T> {
	// Le curseur de la position courante
	/** The cur. */
	private int cur = 0;
	// La liste des elements à afficher
	/** The list. */
	private LinkedList<T> list;
	// la nombre d'élements à afficher
	/** The range. */
	private int range;

	/**
	 * Instantiates a new page.
	 *
	 * @param list the list
	 * @param range the range
	 */
	public Page(LinkedList<T> list, int range) {
		this.list = list;
		this.range = range;
	}

	/**
	 * Permet d'afficher les range éléments avant la position courante.
	 * 
	 */
	private void backward() {
		cur -= (2 * range);
		if (cur < 0) {
			cur = 0;
		}
		for (int i = 0; i < range; i++) {
			System.out.println(list.get(cur + i).toString());
		}
		cur += range;
	}

	/**
	 * Permet d'afficher les range éléments apres la position courante.
	 */
	private void forward() {
		boolean end = false;
		for (int i = 0; i < range; i++) {
			if (cur + i < list.size()) {
				System.out.println(list.get(cur + i).toString());
			} else {
				cur += i;
				end = true;
				break;
			}
		}
		if (!end) {
			cur += range;
		}
	}

	/**
	 * La méthode qui s'occupe de la pagination.
	 */
	public void navigation() {
		Scanner sc = new Scanner(System.in);
		String res = "";
		boolean loop = true;
		while (loop) {
			System.out.println("<- (-) -> (+)  q");
			System.out.println("> ");
			System.out.println(cur);
			res = sc.nextLine();
			switch (res) {
			case "q":
				loop = false;
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
