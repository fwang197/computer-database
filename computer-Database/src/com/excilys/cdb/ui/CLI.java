package com.excilys.cdb.ui;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

import com.excilys.cdb.dao.CompanyDao;
import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.dao.Dao;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class CLI {

	public void lComputer() {
		Dao<Computer> dao = new ComputerDao();
		LinkedList<Computer> l = dao.findAll();
		System.out.println(l.size());
		for (Computer comp : l)
			System.out.println(comp.toString());
	}

	public void lCompany() {
		Dao<Company> dao = new CompanyDao();
		LinkedList<Company> l = dao.findAll();
		System.out.println(l.size());
		for (Company comp : l)
			System.out.println(comp.toString());
	}

	public void show() {
		System.out.println("Computer id ? : ");
		Scanner sc = new Scanner(System.in);
		String res = sc.nextLine();
		try {
			long id = Long.parseLong(res);
			Dao<Computer> compdao = new ComputerDao();
			System.out.println(compdao.find(id).toString());
		} catch (NumberFormatException | NullPointerException e) {
			e.printStackTrace();
			System.out.println("Not Found");
		}
	}

	public void create() {
		Scanner sc = null;
		try {
			Computer c = new Computer();
			sc = new Scanner(System.in);
			System.out.println("Name ? : ");
			c.setName(sc.nextLine());

			System.out.println("Introduced ? : ");
			String tmp = sc.nextLine();
			c.setIntroduced(tmp.equals("null") ? null : java.sql.Timestamp
					.valueOf(tmp));

			System.out.println("Discontinued ? : ");
			tmp = sc.nextLine();
			c.setIntroduced(tmp.equals("null") ? null : java.sql.Timestamp
					.valueOf(tmp));

			System.out.println("Company id ? : ");
			c.setCompany(new CompanyDao().find(sc.nextInt()));

			Dao<Computer> compdao = new ComputerDao();
			compdao.create(c);
			System.out.println("Computer added");
		} catch (NumberFormatException | NullPointerException e) {
			e.printStackTrace();
			System.out.println("Not Correct");
		} catch (IllegalArgumentException e) {
			System.err.println("Date not correct");
		}
	}

	public void update() {
		System.out
				.println("Computer id to update ? (just press enter if you want the value untouched) : ");
		Scanner sc = new Scanner(System.in);
		String res = sc.nextLine();
		try {
			long id = Long.parseLong(res);
			Dao<Computer> compdao = new ComputerDao();
			Computer c = compdao.find(id);
			String tmp = "";
			System.out.println(c.toString());

			System.out.println("Name ? : ");
			tmp = sc.nextLine();
			if (!tmp.equals(""))
				c.setName(tmp);
			System.out.println("Introduced ? : ");
			tmp = sc.nextLine();
			if (!tmp.equals(""))
				c.setIntroduced(tmp.equals("null") ? null : java.sql.Timestamp
						.valueOf(tmp));

			System.out.println("Discontinued ? : ");
			tmp = sc.nextLine();
			if (!tmp.equals(""))
				c.setIntroduced(tmp.equals("null") ? null : java.sql.Timestamp
						.valueOf(tmp));

			System.out.println("Company id ? : ");
			tmp = sc.nextLine();
			if (!tmp.equals(""))
				c.setCompany(new CompanyDao().find(Integer.valueOf(tmp)));

			compdao.update(c);
			System.out.println("Computer updated");
		} catch (NumberFormatException | NullPointerException e) {
			e.printStackTrace();
			System.out.println("Not Found");
		} catch (IllegalArgumentException e) {
			System.err.println("Date not correct");
		}
	}

	public void delete() {
		System.out.println("Computer id to delete ? : ");
		Scanner sc = new Scanner(System.in);
		String res = sc.nextLine();
		try {
			long id = Long.parseLong(res);
			Dao<Computer> compdao = new ComputerDao();
			compdao.delete(compdao.find(id));
			System.out.println("Computer deleted");
		} catch (NumberFormatException | NullPointerException e) {
			e.printStackTrace();
			System.out.println("Not Found");
		}
	}

	public static void printMenu() {
		System.out
				.println("1)List computers\n"
						+ "2)List companies\n"
						+ "3)Show computer details (the detailed information of only one computer)\n"
						+ "4)Create a computer\n" + "5)Update a computer\n"
						+ "6)Delete a computer");
	}

	public static void main(String[] args) {
		CLI cli = new CLI();
		while (true) {
			printMenu();
			System.out.println("Your choice ? : ");
			Scanner sc = new Scanner(System.in);
			try {
				int i = sc.nextInt();
				switch (i) {
				case 1:
					cli.lComputer();
					break;
				case 2:
					cli.lCompany();
					break;
				case 3:
					cli.show();
					break;
				case 4:
					cli.create();
					break;
				case 5:
					cli.update();
					break;
				case 6:
					cli.delete();
					break;
				default:
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("I don't understand!");
			}
		}
	}
}
