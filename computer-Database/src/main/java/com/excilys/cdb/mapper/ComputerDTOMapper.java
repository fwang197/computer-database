package com.excilys.cdb.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.servlet.ComputerDto;
import com.excilys.cdb.tools.Tools;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerDTOMapper.
 */
public class ComputerDTOMapper {

	/**
	 * Convert an object Computer to an object ComputerDto.
	 *
	 * @param computer
	 * @return the computer dto
	 */
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

	/**
	 * Convert a list of Computer to a list of ComputerDto.
	 *
	 * @param computer
	 *            list
	 * @return the list
	 */
	public static List<ComputerDto> toListComputerDto(List<Computer> lcomp) {
		List<ComputerDto> lcompdto = new ArrayList<ComputerDto>();
		lcompdto = lcomp.stream().map(x -> ComputerDTOMapper.toComputerDto(x))
				.collect(Collectors.toList());
		return lcompdto;
	}
}
