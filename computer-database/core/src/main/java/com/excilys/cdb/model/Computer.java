package com.excilys.cdb.model;

import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.excilys.cdb.tools.DateConverter;

@Entity
@Table(name = "computer")
public class Computer {
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	/** The name. */

	private String name;

	/** The introduced. */
	@Convert(converter = DateConverter.class)
	private LocalDateTime introduced;

	/** The discontinued. */
	@Convert(converter = DateConverter.class)
	private LocalDateTime discontinued;

	/** The company. */
	@PrimaryKeyJoinColumn(name = "company_id")
	@ManyToOne
	private Company company;

	/**
	 * Instantiates a new computer.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 * @param introduced
	 *            the introduced
	 * @param discontinued
	 *            the discontinued
	 * @param company
	 *            the company
	 */
	public Computer(ComputerBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.introduced = builder.introduced;
		this.discontinued = builder.discontinued;
		this.company = builder.company;
	}

	/**
	 * Instantiates a new computer.
	 */
	public Computer() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the introduced.
	 *
	 * @return the introduced
	 */
	public LocalDateTime getIntroduced() {
		return introduced;
	}

	/**
	 * Sets the introduced.
	 *
	 * @param introduced
	 *            the new introduced
	 */
	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
	}

	/**
	 * Gets the discontinued.
	 *
	 * @return the discontinued
	 */
	public LocalDateTime getDiscontinued() {
		return discontinued;
	}

	/**
	 * Sets the discontinued.
	 *
	 * @param discontinued
	 *            the new discontinued
	 */
	public void setDiscontinued(LocalDateTime discontinued) {
		this.discontinued = discontinued;
	}

	/**
	 * Gets the company.
	 *
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * Sets the company.
	 *
	 * @param company
	 *            the new company
	 */
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

	public static class ComputerBuilder {
		private long id;
		private String name;
		private LocalDateTime introduced;
		private LocalDateTime discontinued;
		private Company company;

		public ComputerBuilder(String name) {
			this.name = name;
		}

		public ComputerBuilder setId(long id) {
			this.id = id;
			return this;
		}

		public ComputerBuilder setIntroduced(LocalDateTime introduced) {
			this.introduced = introduced;
			return this;
		}

		public ComputerBuilder setDiscontinued(LocalDateTime discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public ComputerBuilder setCompany(Company company) {
			this.company = company;
			return this;
		}

		public Computer build() {
			return new Computer(this);
		}
	}
}
