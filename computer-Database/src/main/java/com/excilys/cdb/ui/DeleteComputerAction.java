package com.excilys.cdb.ui;

import java.util.Scanner;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.tools.Tools;

// TODO: Auto-generated Javadoc
/**
 * L'action responsable de la suppression d'une entrée dans la base Computer.
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

	/**
	 * On demande l'ID d'une machine à l'utilisateur et on supprime cette donnée
	 * dans la base de donnée.
	 */
	@Override
	public void execute() {
		System.out.println("Computer id to delete ? : ");
		System.out.print("> ");
		sc = new Scanner(System.in);
		String res = sc.nextLine();
		if (Tools.isNumber(res)) {
			long id = Long.parseLong(res);
			Computer c = ServiceComputer.INSTANCE.findComputer(id);
			if (!Tools.isNull(c)) {
				ServiceComputer.INSTANCE.deleteComputer(c);
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
