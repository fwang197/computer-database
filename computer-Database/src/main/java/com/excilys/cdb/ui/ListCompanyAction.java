package main.java.com.excilys.cdb.ui;

import main.java.com.excilys.cdb.page.PageCompany;

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
		PageCompany p = new PageCompany(10);
		p.navigation();
	}
}
