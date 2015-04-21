package com.excilys.cdb.mapper;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.servlet.ComputerDto;
import com.excilys.cdb.tools.Tools;

public class ComputerDTOMapper {
	public static ComputerDto toComputerDto(Computer comp) {
		ComputerDto c = null;
		if (!Tools.isNull(comp)) {
			c = new ComputerDto(comp.getId(), comp.getName(),
					comp.getIntroduced() == null ? "" : comp.getIntroduced()
							.toLocalDate().toString(),
					comp.getDiscontinued() == null ? "" : comp
							.getDiscontinued().toLocalDate().toString(),
					comp.getCompany() == null ? 0 : comp.getCompany().getId(),
					comp.getCompany() == null ? "" : comp.getCompany()
							.getName());
		}
		return c;
	}
}
