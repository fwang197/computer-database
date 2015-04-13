package com.excilys.cdb.ui;

import java.util.Scanner;

import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.dao.Dao;
import com.excilys.cdb.model.Computer;

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
		try {
			long id = Long.parseLong(res);
			Dao<Computer> compdao = new ComputerDao();
			compdao.delete(compdao.find(id));
			System.out.println("Computer deleted");
		} catch (NullPointerException e) {
			System.err.println("ID is incorrect!");
		} catch (NumberFormatException e) {
			System.err.println("Computer not found!");
		}
	}

}
