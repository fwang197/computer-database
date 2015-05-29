package com.excilys.cdb.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.excilys.cdb.tools.Tools;

/**
 * The Menu.
 * 
 * @author excilys
 *
 */
public class Menu {

	/** The menu. */
	private static Map<Integer, Action> menu;

	/** The scanner. */
	static Scanner sc = new Scanner(System.in);

	/**
	 * Instantiates a new menu.
	 */
	public Menu() {
		menu = new HashMap<Integer, Action>();
		menu.put(1, new ListComputerAction("List computers"));
		menu.put(2, new ListCompanyAction("List companies"));
		menu.put(
				3,
				new ShowComputerAction(
						"Show computer details (the detailed information of only one computer)"));
		menu.put(4, new CreateComputerAction("Create a computer"));
		menu.put(5, new UpdateComputerAction("Update a computer"));
		menu.put(6, new DeleteComputerAction("Delete a computer"));
		menu.put(7, new DeleteCompanyAction("Delete a company"));
		menu.put(8, new LeaveAppAction("Quit"));
	}

	/**
	 * Print the menu.
	 */
	public void printMenu() {
		for (int i = 1; i <= menu.size(); i++) {
			System.out.println(i + ") " + menu.get(i).description);
		}
		System.out.print("> ");
	}

	/**
	 * Main loop.
	 */
	public void loop() {
		System.out.println("-----COMPUTER DATABASE-----\n");
		while (true) {
			printMenu();
			String choice = sc.nextLine();
			if (Tools.isNumber(choice)) {
				Action a = menu.get(Integer.parseInt(choice));
				if (!Tools.isNull(a)) {
					a.execute();
				} else {
					System.err.println("I don't know that features (yet)!");
				}
			} else {
				System.err.println("I don't understand!");
			}

		}
	}
}
