package com.excilys.cdb.ui;

import java.util.List;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.model.Computer;

/**
 * l'action qui permet d'afficher la liste de toute les entrées dans la table
 * computer.
 * 
 * @author excilys
 *
 */
public class ListComputerAction extends Action {

	/**
	 * Instantiates a new list computer action.
	 *
	 * @param description
	 *            the description
	 */
	public ListComputerAction(String description) {
		this.description = description;
	}

	/**
	 * On récupère la liste des Computer et on l'affiche.
	 */
	@Override
	public void execute() {
		List<Computer> l = Main.computerService.getAll();
		for (Computer comp : l) {
			System.out.println(comp.getId() + " " + comp.toString());
		}
	}
}
