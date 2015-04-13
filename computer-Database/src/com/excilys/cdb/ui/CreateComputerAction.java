package com.excilys.cdb.ui;

import java.util.Scanner;

import com.excilys.cdb.dao.CompanyDao;
import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.dao.Dao;
import com.excilys.cdb.model.Computer;

public class CreateComputerAction extends Action {

	public CreateComputerAction(String description) {
		this.description = description;
	}

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
			c.setIntroduced(tmp.equals("null") ? null : java.sql.Timestamp
					.valueOf(tmp));

			System.out.println("Discontinued ? : ");
			System.out.print("> ");
			tmp = sc.nextLine();
			c.setIntroduced(tmp.equals("null") ? null : java.sql.Timestamp
					.valueOf(tmp));

			System.out.println("Company id ? : ");
			System.out.print("> ");
			c.setCompany(new CompanyDao().find(sc.nextInt()));

			Dao<Computer> compdao = new ComputerDao();
			compdao.create(c);
			System.out.println("Computer added!");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.err.println("Date not correct!");
		}
	}

}
