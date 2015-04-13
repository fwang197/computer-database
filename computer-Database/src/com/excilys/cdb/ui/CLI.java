package com.excilys.cdb.ui;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

import com.excilys.cdb.dao.CompanyDao;
import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.dao.Dao;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

/**
 * La classe CLI permet l'affichage du menu et l'acces aux données mappées.
 * 
 * @author excilys
 *
 */
public class CLI {

	/**
	 * On récupère la liste des Computer et on l'affiche.
	 */
	public void lComputer() {
		Dao<Computer> dao = new ComputerDao();
		LinkedList<Computer> l = dao.findAll();
		System.out.println(l.size());
		for (Computer comp : l)
			System.out.println(comp.toString());
	}

	/**
	 * On récupère la liste des Company et on l'affiche.
	 */
	public void lCompany() {
		Dao<Company> dao = new CompanyDao();
		LinkedList<Company> l = dao.findAll();
		System.out.println(l.size());
		for (Company comp : l)
			System.out.println(comp.toString());
	}

	/**
	 * On demande l'ID d'une machine à l'utilisateur et on affiche ces
	 * caractéristiques.
	 */
	public void show() {
		System.out.println("Computer id ? : ");
		System.out.print("> ");
		Scanner sc = new Scanner(System.in);
		String res = sc.nextLine();
		try {
			long id = Long.parseLong(res);
			Dao<Computer> compdao = new ComputerDao();
			System.out.println(compdao.find(id).toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.err.println("Computer not found!");
		}
	}

	/**
	 * On demande toutes les informations d'une machine à l'utilisateur et on
	 * ajoute cette machine à la base de donnée.
	 */
	public void create() {
		Scanner sc = null;
		try {
			Computer c = new Computer();
			sc = new Scanner(System.in);
			System.out.println("Name ? : ");
			System.out.print("> ");
			c.setName(sc.nextLine());

			System.out.println("Introduced ? : ");
			System.out.print("> ");
			String tmp = sc.nextLine();
			c.setIntroduced(tmp.equals("null") ? null : java.sql.Timestamp
					.valueOf(tmp));

			System.out.println("Discontinued ? : ");
			System.out.print("> ");
			tmp = sc.nextLine();
			c.setIntroduced(tmp.equals("null") ? null : java.sql.Timestamp
					.valueOf(tmp));

			System.out.println("Company id ? : ");
			System.out.print("> ");
			c.setCompany(new CompanyDao().find(sc.nextInt()));

			Dao<Computer> compdao = new ComputerDao();
			compdao.create(c);
			System.out.println("Computer added!");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.err.println("Date not correct!");
		}
	}

	/**
	 * On demande l'ID d'une machine que l'utilisateur veut modifier et on met à
	 * jour cette donnée dans la base de donnée.
	 */
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
			System.out.print("> ");
			tmp = sc.nextLine();
			if (!tmp.equals(""))
				c.setName(tmp);
			System.out.println("Introduced ? : ");
			System.out.print("> ");
			tmp = sc.nextLine();
			if (!tmp.equals(""))
				c.setIntroduced(tmp.equals("null") ? null : java.sql.Timestamp
						.valueOf(tmp));

			System.out.println("Discontinued ? : ");
			System.out.print("> ");
			tmp = sc.nextLine();
			if (!tmp.equals(""))
				c.setIntroduced(tmp.equals("null") ? null : java.sql.Timestamp
						.valueOf(tmp));

			System.out.println("Company id ? : ");
			System.out.print("> ");
			tmp = sc.nextLine();
			if (!tmp.equals(""))
				c.setCompany(new CompanyDao().find(Integer.valueOf(tmp)));

			compdao.update(c);
			System.out.println("Computer updated!");
		} catch (NumberFormatException e) {
			System.err.println("Company ID not found!!");
		} catch (IllegalArgumentException e) {
			System.err.println("Date not correct!");
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * On demande l'ID d'une machine à l'utilisateur et on supprime cette donnée
	 * dans la base de donnée.
	 */
	public void delete() {
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

	/**
	 * Affiche le menu des fonctionnalité.
	 */
	public static void printMenu() {
		System.out
				.println("1)List computers\n"
						+ "2)List companies\n"
						+ "3)Show computer details (the detailed information of only one computer)\n"
						+ "4)Create a computer\n" + "5)Update a computer\n"
						+ "6)Delete a computer");
	}

	public static void main(String[] args) {
		System.out.println("-----COMPUTER DATABASE-----\n");
		CLI cli = new CLI();
		while (true) {
			printMenu();
			System.out.println("Your choice ? : ");
			System.out.print("> ");
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
				System.err.println("I don't understand!");
			}
		}
	}
}
