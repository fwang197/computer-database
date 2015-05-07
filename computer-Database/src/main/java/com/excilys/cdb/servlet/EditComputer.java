package com.excilys.cdb.servlet;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.dao.DateMapper;
import com.excilys.cdb.mapper.ComputerDTOMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.IServiceCompany;
import com.excilys.cdb.service.IServiceComputer;
import com.excilys.cdb.tools.Tools;
import com.excilys.cdb.tools.Validator;

@Controller
@RequestMapping("/EditComputer")
public class EditComputer {

	@Autowired
	private IServiceComputer servicecomputer;

	@Autowired
	private IServiceCompany servicecompany;

	public EditComputer() {
		super();
	}

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(
			@RequestParam(value = "compId", required = false) String sid,
			ModelMap model) {
		long id = 0;
		if (Tools.isNumber(sid)) {
			id = Long.parseLong(sid);
			if (servicecomputer.findComputer(id) == null) {
				id = 1;
			}
		}

		ArrayList<Company> lcomp = new ArrayList<Company>(
				servicecompany.findAllCompany());
		model.addAttribute("list", lcomp);

		ComputerDto comp = ComputerDTOMapper.toComputerDto(servicecomputer
				.findComputer(id));
		model.addAttribute("compId", comp.getId());
		model.addAttribute("name", comp.getName());
		model.addAttribute("intro", comp.getIntroduced());
		model.addAttribute("discon", comp.getDiscontinued());
		model.addAttribute("compName", comp.getCompanyName());
		model.addAttribute("companyId", comp.getCompanyId());
		model.addAttribute("list", lcomp);
		return "editComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(
			@RequestParam(value = "computerName", required = false) String name,
			@RequestParam(value = "introduced", required = false) String introduced,
			@RequestParam(value = "discontinued", required = false) String discontinued,
			@RequestParam(value = "companyId", required = false) String scompanyid,
			@RequestParam(value = "compId", required = false) String scomputerid,
			ModelMap model) {

		ArrayList<Company> lcomp = new ArrayList<Company>(
				servicecompany.findAllCompany());
		model.addAttribute("list", lcomp);

		if (!name.equals("") && Validator.isDateValid(introduced)
				&& Validator.isDateValid(discontinued)) {
			Company company = null;
			if (Tools.isNumber(scompanyid)) {
				company = servicecompany
						.findCompany(Long.parseLong(scompanyid));
			}
			if (Tools.isNumber(scomputerid)) {

				servicecomputer.updateComputer(new Computer.ComputerBuilder(
						name).setId(Long.parseLong(scomputerid))
						.setIntroduced(DateMapper.toDateFormat(introduced))
						.setDiscontinued(DateMapper.toDateFormat(discontinued))
						.setCompany(company).build());
				return "dashboard";
			}
			return "editComputer";
		} else {
			model.addAttribute("compId", Long.parseLong(scomputerid));
			return "editComputer";
		}
	}
}
