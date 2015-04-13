package com.excilys.cdb.ui;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * La classe représente le menu de notre application. Notre menu est une map où
 * la clée est le numero de la fonctionnalité et la valeur est la
 * fonctionnalité.
 * 
 * @author excilys
 *
 */
public class Menu {

	// Le menu
	private static HashMap<Integer, Action> menu;
	static Scanner sc = new Scanner(System.in);
	// Permet de savoir si on quitte l'application
	static boolean loop = true;

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
		menu.put(7, new LeaveAppAction("Quit"));
	}

	/**
	 * Affiche le menu des fonctionnalité.
	 */
	public void printMenu() {
		for (int i = 1; i <= menu.size(); i++) {
			System.out.println(i + ") " + menu.get(i).description);
		}
		System.out.print("> ");
	}

	/**
	 * La boucle principale de notre application.
	 */
	public void loop() {
		System.out.println("-----COMPUTER DATABASE-----\n");
		while (loop) {
			try {
				printMenu();
				int choice = sc.nextInt();
				menu.get(choice).execute();
			} catch (InputMismatchException e) {
				System.err.println("I don't understand!");
			}
		}
	}
}
