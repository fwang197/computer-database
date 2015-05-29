package com.excilys.cdb.ui;

import java.util.List;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.model.Company;

/**
 * Get the List of all the companies.
 * 
 * @author excilys
 *
 */
public class ListCompanyAction extends Action {

	/**
	 * Instantiates a new list company action.
	 *
	 * @param description
	 *            the description
	 */
	public ListCompanyAction(String description) {
		this.description = description;
	}

	public void execute() {
		List<Company> l = Main.companyService.getAll();
		for (Company comp : l) {
			System.out.println(comp.getId() + " " + comp.toString());
		}
	}
}
