package com.excilys.cdb.ui;

import java.util.Scanner;

import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.dao.Dao;
import com.excilys.cdb.model.Computer;

public class DeleteComputerAction extends Action {

	public DeleteComputerAction(String description) {
		this.description = description;
	}

	@Override
	public void execute() {
		System.out.println("Computer id to delete ? : ");
		System.out.print("> ");
		Scanner sc = new Scanner(System.in);
		String res = sc.nextLine();
		try {
			long id = Long.parseLong(res);
			Dao<Computer> compdao = new ComputerDao();
			compdao.delete(compdao.find(id));
			System.out.println("Computer deleted");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.err.println("Computer not found!");
		}
	}

}
