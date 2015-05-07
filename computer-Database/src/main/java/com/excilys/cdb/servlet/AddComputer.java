package com.excilys.cdb.servlet;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.dao.DateMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.IServiceCompany;
import com.excilys.cdb.service.IServiceComputer;
import com.excilys.cdb.tools.Tools;
import com.excilys.cdb.tools.Validator;

@Controller
@RequestMapping("/AddComputer")
public class AddComputer {

	@Autowired
	private IServiceCompany servicecompany;

	@Autowired
	private IServiceComputer servicecomputer;

	public AddComputer() {
		super();
	}

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(ModelMap model) {

		ArrayList<Company> lcomp = new ArrayList<Company>(
				servicecompany.findAllCompany());
		model.addAttribute("list", lcomp);
		return "addComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(
			@RequestParam(value = "computerName", required = false) String name,
			@RequestParam(value = "introduced", required = false) String intro,
			@RequestParam(value = "discontinued", required = false) String discon,
			@RequestParam(value = "id", required = false) String id,
			ModelMap model) {

		ArrayList<Company> lcomp = new ArrayList<Company>(
				servicecompany.findAllCompany());
		model.addAttribute("list", lcomp);

		if (!name.equals("") && Validator.isDateValid(intro)
				&& Validator.isDateValid(discon)) {
			Company comp = null;

			if (Tools.isNumber(id)) {
				comp = servicecompany.findCompany(Long.parseLong(id));
			}
			servicecomputer.createComputer(new Computer.ComputerBuilder(name)
					.setIntroduced(DateMapper.toDateFormat(intro))
					.setDiscontinued(DateMapper.toDateFormat(discon))
					.setCompany(comp).build());
			return "dashboard";
		} else {
			return "addComputer";
		}
	}
}
