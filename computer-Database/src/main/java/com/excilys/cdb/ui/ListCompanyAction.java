package com.excilys.cdb.ui;

import java.util.ArrayList;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.ServiceCompany;

// TODO: Auto-generated Javadoc
/**
 * l'action qui permet d'afficher la liste de toute les entrées dans la table
 * company.
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

	/**
	 * On récupère la liste des Company et on l'affiche.
	 */
	@Override
	public void execute() {
		ArrayList<Company> l = new ArrayList<Company>(
				ServiceCompany.INSTANCE.findAllCompany());
		for (Company comp : l) {
			System.out.println(comp.getId() + " " + comp.toString());
		}
	}
}
