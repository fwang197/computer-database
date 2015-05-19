package com.excilys.cdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.IServiceComputer;

@Controller
@RequestMapping("/DeleteComputer")
public class DeleteComputer {

	@Autowired
	private IServiceComputer servicecomputer;

	public DeleteComputer() {
		super();
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(
			@RequestParam(value = "selection", required = false) String res) {
		for (String s : res.split(",")) {
			if (!s.equals("")) {
				servicecomputer.deleteComputer(new Computer.ComputerBuilder("")
						.setId(Long.parseLong(s)).build());
			}
		}
		return "redirect:/";
	}
}
