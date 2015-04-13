package com.excilys.cdb.ui;

import java.util.Scanner;

import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.dao.Dao;
import com.excilys.cdb.model.Computer;

/**
 * L'action qui permet d'avoir le detail d'une entrée de la table computer.
 * 
 * @author excilys
 *
 */
public class ShowComputerAction extends Action {

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
		Scanner sc = new Scanner(System.in);
		String res = sc.nextLine();
		try {
			long id = Long.parseLong(res);
			Dao<Computer> compdao = new ComputerDao();
			System.out.println(compdao.find(id).toString());
		} catch (NumberFormatException e) {
			System.err.println("ID is incorrect!");
		} catch (NullPointerException e) {
			System.err.println("Computer not found!");
		}
	}

}
