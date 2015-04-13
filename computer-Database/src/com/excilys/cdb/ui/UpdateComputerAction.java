package com.excilys.cdb.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.excilys.cdb.dao.CompanyDao;
import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.dao.Dao;
import com.excilys.cdb.model.Computer;

/**
 * L'action responsable de la mise à jour d'une entrée dans la base Computer.
 * 
 * @author excilys
 *
 */
public class UpdateComputerAction extends Action {

	public UpdateComputerAction(String description) {
		this.description = description;
	}

	/**
	 * On demande l'ID d'une machine que l'utilisateur veut modifier et on met à
	 * jour cette donnée dans la base de donnée.
	 */
	@Override
	public void execute() {
		System.out
				.println("Computer id to update ? (just press enter if you want the value untouched) : ");
		Scanner sc = new Scanner(System.in);
		String res = sc.nextLine();
		try {
			long id = Long.parseLong(res);
			Dao<Computer> compdao = new ComputerDao();
			Computer c = compdao.find(id);
			String tmp = "";
			System.out.println(c.toString());

			System.out.println("Name ? : ");
			System.out.print("> ");
			tmp = sc.nextLine();
			if (!tmp.equals(""))
				c.setName(tmp);
			System.out.println("Introduced ? : ");
			System.out.print("> ");
			tmp = sc.nextLine();
			if (!tmp.equals(""))
				c.setIntroduced(tmp.equals("null") ? null : java.sql.Timestamp
						.valueOf(tmp));

			System.out.println("Discontinued ? : ");
			System.out.print("> ");
			tmp = sc.nextLine();
			if (!tmp.equals(""))
				c.setIntroduced(tmp.equals("null") ? null : java.sql.Timestamp
						.valueOf(tmp));

			System.out.println("Company id ? : ");
			System.out.print("> ");
			tmp = sc.nextLine();
			if (!tmp.equals(""))
				c.setCompany(new CompanyDao().find(Integer.valueOf(tmp)));

			compdao.update(c);
			System.out.println("Computer updated!");
		} catch (NumberFormatException | NullPointerException
				| InputMismatchException e) {
			System.err.println("ID is incorrect!");
		} catch (IllegalArgumentException e) {
			System.err.println("Date format is incorrect!");
		}
	}
}
