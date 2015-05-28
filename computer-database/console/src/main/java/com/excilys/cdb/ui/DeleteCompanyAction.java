package com.excilys.cdb.ui;

import java.util.Scanner;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.tools.Tools;

public class DeleteCompanyAction extends Action {

	private Scanner sc;

	public DeleteCompanyAction(String description) {
		this.description = description;

	}

	@Override
	public void execute() {
		System.out.println("Company id to delete ? : ");
		System.out.print("> ");
		sc = new Scanner(System.in);
		String res = sc.nextLine();
		if (Tools.isNumber(res)) {
			long id = Long.parseLong(res);
			Company c = Main.companyService.getCompany(id);
			if (!Tools.isNull(c)) {
				Main.companyService.delete(id);
				System.out.println("Company deleted");
			} else {
				System.err.println("Company not found!");
				return;
			}
		} else {
			System.err.println("ID is incorrect!");
			return;
		}
	}

}
