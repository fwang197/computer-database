package com.excilys.cdb.ui;

import java.util.Scanner;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.ServiceCompany;
import com.excilys.cdb.tools.Tools;

public class DeleteCompanyAction extends Action {

	public DeleteCompanyAction(String description) {
		this.description = description;
	}

	@Override
	public void execute() {
		System.out.println("Company id to delete ? : ");
		System.out.print("> ");
		Scanner sc = new Scanner(System.in);
		String res = sc.nextLine();
		if (Tools.isNumber(res)) {
			long id = Long.parseLong(res);
			Company c = ServiceCompany.INSTANCE.findCompany(id);
			if (!Tools.isNull(c)) {
				ServiceCompany.INSTANCE.deleteCompany(c);
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
