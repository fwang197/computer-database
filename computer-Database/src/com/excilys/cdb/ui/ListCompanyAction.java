package com.excilys.cdb.ui;

import java.util.LinkedList;

import com.excilys.cdb.dao.CompanyDao;
import com.excilys.cdb.dao.Dao;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.page.Page;

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
		Dao<Company> dao = new CompanyDao();
		LinkedList<Company> l = (LinkedList<Company>) dao.findAll();
		Page<Company> p = new Page<Company>(l, 20);
		p.navigation();
		// for (Company comp : l)
		// System.out.println(comp.toString());
	}

}
