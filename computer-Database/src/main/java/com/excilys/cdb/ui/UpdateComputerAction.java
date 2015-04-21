package com.excilys.cdb.ui;

import java.util.Scanner;

import com.excilys.cdb.dao.DateMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ServiceCompany;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.tools.Tools;

// TODO: Auto-generated Javadoc
/**
 * L'action responsable de la mise à jour d'une entrée dans la base Computer.
 * 
 * @author excilys
 *
 */
public class UpdateComputerAction extends Action {

	/**
	 * Instantiates a new update computer action.
	 *
	 * @param description
	 *            the description
	 */
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
			if (Tools.isNumber(res)) {
				long id = Long.parseLong(res);
				Computer c = ServiceComputer.INSTANCE.findComputer(id);
				if (!Tools.isNull(c)) {
					System.out.println(c.toString());

					System.out.println("Name ? : ");
					System.out.print("> ");
					res = sc.nextLine();
					if (!res.equals(""))
						c.setName(res);

					System.out.println("Introduced ? : ");
					System.out.print("> ");
					res = sc.nextLine();
					if (!res.equals(""))
						c.setIntroduced(res.equals("null") ? null : DateMapper
								.toLocalDateTime(java.sql.Timestamp
										.valueOf(res)));

					System.out.println("Discontinued ? : ");
					System.out.print("> ");
					res = sc.nextLine();
					if (!res.equals(""))
						c.setIntroduced(res.equals("null") ? null : DateMapper
								.toLocalDateTime(java.sql.Timestamp
										.valueOf(res)));

					System.out.println("Company id ? : ");
					System.out.print("> ");
					res = sc.nextLine();
					if (!res.equals("")) {
						if (Tools.isNumber(res)) {
							Company comp = ServiceCompany.INSTANCE
									.findCompany(Long.valueOf(res));
							if (!Tools.isNull(comp)) {
								c.setCompany(comp);
							} else {
								System.err.println("ID is incorrect!");
								return;
							}
						} else {
							System.err.println("ID is incorrect!");
							return;
						}
					}
					ServiceComputer.INSTANCE.updateComputer(c);
					System.out.println("Computer updated!");
				} else {
					System.err.println("ID is incorrect!");
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
