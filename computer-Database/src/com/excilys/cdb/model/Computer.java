package com.excilys.cdb.model;

import java.sql.Timestamp;

/**
 * La classe Computer représente une machine. Elle possede un nom , les dates de
 * mise en circulation et d'arret de la production ainsi que la compagnie qui
 * l'a créée.
 * 
 * @author excilys
 *
 */
public class Computer {
	// ID
	private long id;
	// Nom de la machine
	private String name;
	// Date de debut de distribution
	private Timestamp introduced;
	// Date de fin de distribution
	private Timestamp discontinued;
	// Compagnie à l'origine du produit
	private Company company;

	public Computer(long id, String name, Timestamp introduced,
			Timestamp discontinued, Company company) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}

	public Computer() {
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Timestamp introduced) {
		this.introduced = introduced;
	}

	public Timestamp getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Timestamp discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String toString() {
		String str = "ID : " + this.getId() + "\n";
		str += "NAME : " + this.getName() + "\n";
		str += "Introduced : " + this.getIntroduced() + "\n";
		str += "Discontinued : " + this.getDiscontinued() + "\n";
		str += "Company : "
				+ (this.company == null ? null : this.getCompany().getName())
				+ "\n";
		str += "\n.....................................\n";

		return str;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
