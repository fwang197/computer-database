package com.excilys.cdb.ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.ServiceCompany;
import com.excilys.cdb.tools.Tools;

@Controller
public class DeleteCompanyAction extends Action {

	private Scanner sc;

	@Autowired
	private ServiceCompany servicecompany;

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
			Company c = servicecompany.findCompany(id);
			if (!Tools.isNull(c)) {
				servicecompany.deleteCompany(c);
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
