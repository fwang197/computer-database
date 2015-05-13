package com.excilys.cdb.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.excilys.cdb.tools.DateConstraint;

public class ComputerDto {

	private long compId;
	@NotNull
	@Size(min = 2, max = 64)
	private String name;
	@DateConstraint
	private String introduced;
	@DateConstraint
	private String discontinued;
	private long companyId;
	private String companyName;

	public ComputerDto() {

	}

	public ComputerDto(ComputerDtoBuilder builder) {
		this.compId = builder.compId;
		this.name = builder.name;
		this.introduced = builder.introduced;
		this.discontinued = builder.discontinued;
		this.companyId = builder.companyId;
		this.companyName = builder.companyName;
	}

	public long getCompId() {
		return compId;
	}

	public void setCompId(long id) {
		this.compId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComputerDto other = (ComputerDto) obj;
		if (companyId != other.companyId)
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (compId != other.compId)
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

	public String toString() {
		return "ComputerDto [id=" + compId + ", name=" + name + ", introduced="
				+ introduced + ", discontinued=" + discontinued
				+ ", companyId=" + companyId + ", companyName=" + companyName
				+ "]";
	}

	public static class ComputerDtoBuilder {
		private long compId;
		private String name;
		private String introduced;
		private String discontinued;
		private long companyId;
		private String companyName;

		public ComputerDtoBuilder(String name) {
			this.name = name;
		}

		public ComputerDtoBuilder setCompId(long id) {
			this.compId = id;
			return this;
		}

		public ComputerDtoBuilder setIntroduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

		public ComputerDtoBuilder setDiscontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public ComputerDtoBuilder setCompanyId(long cid) {
			this.companyId = cid;
			return this;
		}

		public ComputerDtoBuilder setCompanyName(String cname) {
			this.companyName = cname;
			return this;
		}

		public ComputerDto build() {
			return new ComputerDto(this);
		}
	}

}
