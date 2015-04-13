package com.excilys.cdb.ui;

import java.util.LinkedList;

import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.dao.Dao;
import com.excilys.cdb.model.Computer;

/**
 * l'action qui permet d'afficher la liste de toute les entrées dans la table
 * computer.
 * 
 * @author excilys
 *
 */
public class ListComputerAction extends Action {

	public ListComputerAction(String description) {
		this.description = description;
	}

	/**
	 * On récupère la liste des Computer et on l'affiche.
	 */
	@Override
	public void execute() {
		Dao<Computer> dao = new ComputerDao();
		LinkedList<Computer> l = dao.findAll();
		System.out.println(l.size());
		for (Computer comp : l)
			System.out.println(comp.toString());
	}

}
