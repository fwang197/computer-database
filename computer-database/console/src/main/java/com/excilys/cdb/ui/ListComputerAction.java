package com.excilys.cdb.ui;

import java.util.List;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.model.Computer;

/**
 * Get the list of all the computers.
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

	public void execute() {
		List<Computer> l = Main.computerService.getAll();
		for (Computer comp : l) {
			System.out.println(comp.getId() + " " + comp.toString());
		}
	}
}
