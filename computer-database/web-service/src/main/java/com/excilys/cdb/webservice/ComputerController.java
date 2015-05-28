package com.excilys.cdb.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.mapper.ComputerDTOMapper;
import com.excilys.cdb.mapper.ComputerDto;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.IComputerService;

@RestController
@RequestMapping("/computer")
public class ComputerController {

	@Autowired
	private IComputerService serviceComputer;

	@RequestMapping(method = RequestMethod.GET, value = "/getAll")
	public Object[] getAll() {
		return ComputerDTOMapper.toListComputerDto(
				serviceComputer.findAllComputer()).toArray();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ComputerDto getComputer(@RequestParam(value = "id") Long id) {
		ComputerDto c = ComputerDTOMapper.toComputerDto(serviceComputer
				.findComputer(id));
		return c;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/create")
	public void createComputer(@RequestBody ComputerDto computerDto) {
		serviceComputer.createComputer(ComputerDTOMapper
				.toComputer(computerDto));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/update")
	public void updateComputer(@RequestBody ComputerDto computerDto) {
		serviceComputer.updateComputer(ComputerDTOMapper
				.toComputer(computerDto));
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/delete")
	public void deleteComputer(@RequestParam(value = "id") long id) {
		Computer c = serviceComputer.findComputer(id);
		serviceComputer.deleteComputer(c);
	}
}
