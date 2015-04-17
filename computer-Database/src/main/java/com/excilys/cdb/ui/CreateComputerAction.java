package main.java.com.excilys.cdb.ui;

import java.util.Scanner;

import main.java.com.excilys.cdb.mapper.Mapper;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.service.ServiceCompany;
import main.java.com.excilys.cdb.service.ServiceComputer;
import main.java.com.excilys.cdb.tools.Tools;

// TODO: Auto-generated Javadoc
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
			c.setIntroduced(tmp.equals("null") ? null : Mapper
					.toLocalDateTime(java.sql.Timestamp.valueOf(tmp)));

			System.out.println("Discontinued ? : ");
			System.out.print("> ");
			tmp = sc.nextLine();
			c.setIntroduced(tmp.equals("null") ? null : Mapper
					.toLocalDateTime(java.sql.Timestamp.valueOf(tmp)));

			System.out.println("Company id ? : ");
			System.out.print("> ");

			String res = sc.nextLine();
			if (Tools.isNumber(res)) {
				Company comp = ServiceCompany.INSTANCE.findCompany(Long
						.parseLong(res));
				if (!Tools.isNull(comp)) {
					c.setCompany(comp);
					ServiceComputer.INSTANCE.createComputer(c);
					System.out.println("Computer added!");
				} else {
					System.err.println("Company ID is incorrect!");
					return;
				}
			} else {
				System.err.println("ID is incorrect!");
				return;
			}
		} catch (IllegalArgumentException e) {
			System.err.println("Date format is incorrect!");
		}
	}
}
