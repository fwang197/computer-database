package com.excilys.cdb.ui;

import java.util.Scanner;

import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.dao.Dao;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.tools.Tools;

/**
 * L'action responsable de la suppression d'une entrée dans la base Computer.
 * 
 * @author excilys
 *
 */
public class DeleteComputerAction extends Action {

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
		Scanner sc = new Scanner(System.in);
		String res = sc.nextLine();
		if (Tools.isNumber(res)) {
			long id = Long.parseLong(res);
			Dao<Computer> compdao = new ComputerDao();
			Computer c = compdao.find(id);
			if (Tools.isNull(c)) {
				compdao.delete(c);
				System.out.println("Computer deleted");
			} else {

				System.err.println("Computer not found!");
			}
		} else {
			System.err.println("ID is incorrect!");
		}
	}
}
