package com.excilys.cdb.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.cdb.mapper.ComputerDTOMapper;
import com.excilys.cdb.mapper.ComputerDto;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.ICompanyService;
import com.excilys.cdb.service.IComputerService;

@Controller
@RequestMapping("/AddComputer")
public class AddComputer {

	@Autowired
	private ICompanyService servicecompany;

	@Autowired
	private IComputerService servicecomputer;

	public AddComputer() {
		super();
	}

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(ModelMap model) {
		List<Company> lcomp = servicecompany.findAllCompany();
		model.addAttribute("computerDto", new ComputerDto());
		model.addAttribute("list", lcomp);
		return "addComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(@ModelAttribute @Valid ComputerDto computerDto,
			BindingResult bindingResult, ModelMap model) {

		List<Company> lcomp = servicecompany.findAllCompany();
		model.addAttribute("list", lcomp);
		if (!bindingResult.hasErrors()) {
			servicecomputer.createComputer(ComputerDTOMapper
					.toComputer(computerDto));
			return "redirect:/";
		} else {

			return "addComputer";
		}
	}
}
