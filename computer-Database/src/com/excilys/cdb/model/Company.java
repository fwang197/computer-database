package com.excilys.cdb.model;

/**
 * La classe Company représente une compagnie avec seulement un nom.
 * 
 * @author excilys
 *
 */
public class Company {
	private long id;
	private String name;

	public Company(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Company(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		String str = "ID : " + this.getId() + "\n";
		str += "NAME : " + this.getName() + "\n";
		str += "\n.....................................\n";
		return str;
	}
}
