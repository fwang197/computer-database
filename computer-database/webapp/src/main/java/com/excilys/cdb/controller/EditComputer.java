package com.excilys.cdb.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.mapper.ComputerDTOMapper;
import com.excilys.cdb.mapper.ComputerDto;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ICompanyService;
import com.excilys.cdb.service.IComputerService;

@Controller
@RequestMapping("/EditComputer")
public class EditComputer {

	@Autowired
	private IComputerService servicecomputer;

	@Autowired
	private ICompanyService servicecompany;

	public EditComputer() {
		super();
	}

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(
			@RequestParam(value = "compId", required = false) long id,
			ModelMap model) {
		if (servicecomputer.findComputer(id) != null) {

			ArrayList<Company> lcomp = new ArrayList<Company>(
					servicecompany.findAllCompany());
			model.addAttribute("list", lcomp);

			ComputerDto comp = ComputerDTOMapper.toComputerDto(servicecomputer
					.findComputer(id));
			model.addAttribute("computerDto", comp);
			model.addAttribute("list", lcomp);
			return "editComputer";
		}
		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(@ModelAttribute @Valid ComputerDto computerDto,
			BindingResult bindingResult, ModelMap model) {

		ArrayList<Company> lcomp = new ArrayList<Company>(
				servicecompany.findAllCompany());
		model.addAttribute("list", lcomp);

		if (!bindingResult.hasErrors()) {
			Computer c = ComputerDTOMapper.toComputer(computerDto);
			c.setCompany(servicecompany.findCompany(computerDto.getCompanyId()));
			servicecomputer.updateComputer(c);
			return "redirect:/";

		} else {
			model.addAttribute("computerDto", computerDto);
			return "editComputer";
		}
	}
}
