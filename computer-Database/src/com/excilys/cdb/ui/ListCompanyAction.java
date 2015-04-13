package com.excilys.cdb.ui;

import java.util.LinkedList;

import com.excilys.cdb.dao.CompanyDao;
import com.excilys.cdb.dao.Dao;
import com.excilys.cdb.model.Company;

public class ListCompanyAction extends Action {

	public ListCompanyAction(String description) {
		this.description = description;
	}

	@Override
	public void execute() {
		Dao<Company> dao = new CompanyDao();
		LinkedList<Company> l = dao.findAll();
		System.out.println(l.size());
		for (Company comp : l)
			System.out.println(comp.toString());
	}

}
