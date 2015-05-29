package com.excilys.cdb.ui;

import java.util.Scanner;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.tools.Tools;

/**
 * Deletion of a Computer.
 * 
 * @author excilys
 *
 */
public class DeleteComputerAction extends Action {

	private Scanner sc;

	/**
	 * Instantiates a new delete computer action.
	 *
	 * @param description
	 *            the description
	 */
	public DeleteComputerAction(String description) {
		this.description = description;
	}

	public void execute() {
		System.out.println("Computer id to delete ? : ");
		System.out.print("> ");
		sc = new Scanner(System.in);
		String res = sc.nextLine();
		if (Tools.isNumber(res)) {
			long id = Long.parseLong(res);
			Computer c = Main.computerService.getComputer(id);
			if (!Tools.isNull(c)) {
				Main.computerService.delete(id);
				System.out.println("Computer deleted");
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
