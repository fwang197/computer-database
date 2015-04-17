package main.java.com.excilys.cdb.ui;

import java.util.ArrayList;

import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.service.ServiceComputer;

// TODO: Auto-generated Javadoc
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
		ArrayList<Computer> l = new ArrayList<Computer>(
				ServiceComputer.INSTANCE.findAllComputer());
		for (Computer comp : l)
			System.out.println(comp.getId() + " " + comp.toString());
	}
}
