package com.excilys.cdb.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.tools.DateMapper;
import com.excilys.cdb.tools.Tools;

/**
 * The Class ComputerDTOMapper.
 */
public class ComputerDTOMapper {

	/**
	 * Convert an object Computer to an object ComputerDto.
	 *
	 * @param comp
	 *            Computer
	 * @return ComputerDto
	 */
	public static ComputerDto toComputerDto(Computer comp) {
		ComputerDto c = null;

		if (!Tools.isNull(comp)) {
			c = new ComputerDto.ComputerDtoBuilder(comp.getName())
					.setCompId(comp.getId())
					.setIntroduced(
							comp.getIntroduced() == null ? "" : DateMapper
									.toString(comp.getIntroduced()))
					.setDiscontinued(
							comp.getDiscontinued() == null ? "" : DateMapper
									.toString(comp.getDiscontinued()))
					.setCompanyId(
							comp.getCompany() == null ? 0 : comp.getCompany()
									.getId())
					.setCompanyName(
							comp.getCompany() == null ? "" : comp.getCompany()
									.getName()).build();

		}
		return c;
	}

	/**
	 * Convert an object ComputerDto to an object Computer.
	 *
	 * @param comp
	 *            ComputerDto
	 * @return Computer
	 */
	public static Computer toComputer(ComputerDto comp) {
		Computer c = null;
		if (!Tools.isNull(comp)) {
			c = new Computer.ComputerBuilder(comp.getName())
					.setId(comp.getCompId())
					.setIntroduced(
							comp.getIntroduced() == null
									|| comp.getIntroduced().isEmpty() ? null
									: DateMapper.toDateFormat(comp
											.getIntroduced()))
					.setDiscontinued(
							comp.getDiscontinued() == null
									|| comp.getDiscontinued().isEmpty() ? null
									: DateMapper.toDateFormat(comp
											.getDiscontinued()))
					.setCompany(
							comp.getCompanyName() == null
									|| comp.getCompanyName().isEmpty() ? null
									: new Company.CompanyBuilder(comp
											.getCompanyName()).setId(
											comp.getCompanyId()).build())
					.build();
		}
		return c;

	}

	/**
	 * Convert a list of Computer to a list of ComputerDto.
	 *
	 * @param lcomp
	 *            List of Computer
	 * @return List of ComputerDto
	 */
	public static List<ComputerDto> toListComputerDto(List<Computer> lcomp) {
		List<ComputerDto> lcompdto = new ArrayList<ComputerDto>();
		lcompdto = lcomp.stream().map(x -> ComputerDTOMapper.toComputerDto(x))
				.collect(Collectors.toList());
		return lcompdto;
	}

	/**
	 * Convert a list of ComputerDto to a list of Computer.
	 *
	 * @param lcompdto
	 *            List of ComputerDto
	 * 
	 * @return List of Computer
	 */
	public static List<Computer> toListComputer(List<ComputerDto> lcompdto) {
		List<Computer> lcomp = new ArrayList<Computer>();
		lcomp = lcompdto.stream().map(x -> ComputerDTOMapper.toComputer(x))
				.collect(Collectors.toList());
		return lcomp;
	}

}
