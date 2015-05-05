package com.excilys.cdb.ui;

import java.util.Scanner;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.tools.Tools;

// TODO: Auto-generated Javadoc
/**
 * L'action qui permet d'avoir le detail d'une entrée de la table computer.
 * 
 * @author excilys
 *
 */
public class ShowComputerAction extends Action {

	private Scanner sc;

	private ServiceComputer servicecomputer;

	/**
	 * Instantiates a new show computer action.
	 *
	 * @param description
	 *            the description
	 */
	public ShowComputerAction(String description) {
		this.description = description;
	}

	/**
	 * On demande l'ID d'une machine à l'utilisateur et on affiche ces
	 * caractéristiques.
	 */
	@Override
	public void execute() {
		System.out.println("Computer id ? : ");
		System.out.print("> ");
		sc = new Scanner(System.in);
		String res = sc.nextLine();
		if (Tools.isNumber(res)) {
			long id = Long.parseLong(res);
			Computer c = servicecomputer.findComputer(id);
			if (!Tools.isNull(c)) {
				System.out.println(c.toString());
			} else {
				System.err.println("Computer not found!");
				return;
			}
		} else {
			System.err.println("ID is incorrect!");
			return;
		}

	}
}
