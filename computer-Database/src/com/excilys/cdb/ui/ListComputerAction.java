package com.excilys.cdb.ui;

import java.util.LinkedList;

import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.dao.Dao;
import com.excilys.cdb.model.Computer;

public class ListComputerAction extends Action {

	public ListComputerAction(String description) {
		this.description = description;
	}

	@Override
	public void execute() {
		Dao<Computer> dao = new ComputerDao();
		LinkedList<Computer> l = dao.findAll();
		System.out.println(l.size());
		for (Computer comp : l)
			System.out.println(comp.toString());
	}

}
