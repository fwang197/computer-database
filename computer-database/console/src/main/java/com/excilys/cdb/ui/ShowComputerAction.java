package com.excilys.cdb.ui;

import java.util.Scanner;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.tools.Tools;

/**
 * Get the detail of a single Computer.
 * 
 * @author excilys
 *
 */
public class ShowComputerAction extends Action {

	private Scanner sc;

	/**
	 * Instantiates a new show computer action.
	 *
	 * @param description
	 *            the description
	 */
	public ShowComputerAction(String description) {
		this.description = description;
	}

	public void execute() {
		System.out.println("Computer id ? : ");
		System.out.print("> ");
		sc = new Scanner(System.in);
		String res = sc.nextLine();
		if (Tools.isNumber(res)) {
			long id = Long.parseLong(res);
			Computer c = Main.computerService.getComputer(id);
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
