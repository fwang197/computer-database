package com.excilys.cdb.ui;

import java.util.Scanner;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.tools.DateMapper;
import com.excilys.cdb.tools.Tools;

/**
 * L'action responsable de la creation d'une entrée dans la base Computer.
 * 
 * @author excilys
 *
 */
public class CreateComputerAction extends Action {

	/**
	 * Instantiates a new creates the computer action.
	 *
	 * @param description
	 *            the description
	 */
	public CreateComputerAction(String description) {
		this.description = description;

	}

	/**
	 * On demande toutes les informations d'une machine à l'utilisateur et on
	 * ajoute cette machine à la base de donnée.
	 */
	@Override
	public void execute() {
		Scanner sc = null;
		try {
			Computer c = new Computer();
			sc = new Scanner(System.in);
			System.out.println("Name ? : ");
			System.out.print("> ");
			c.setName(sc.nextLine());

			System.out.println("Introduced ? : ");
			System.out.print("> ");
			String tmp = sc.nextLine();
			c.setIntroduced(tmp.equals("null") ? null : DateMapper
					.toLocalDateTime(java.sql.Timestamp.valueOf(tmp)));

			System.out.println("Discontinued ? : ");
			System.out.print("> ");
			tmp = sc.nextLine();
			c.setIntroduced(tmp.equals("null") ? null : DateMapper
					.toLocalDateTime(java.sql.Timestamp.valueOf(tmp)));

			System.out.println("Company id ? : ");
			System.out.print("> ");

			String res = sc.nextLine();
			if (Tools.isNumber(res)) {
				Company comp = Main.companyService.getCompany(Long
						.parseLong(res));
				c.setCompany(comp);
				Main.computerService.create(c);
				System.out.println("Computer added!");

			} else {
				System.err.println("Company ID is incorrect!");
				return;
			}
		} catch (IllegalArgumentException e) {
			System.err.println("Date format is incorrect!");
		}
	}
}
