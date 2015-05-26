package com.excilys.cdb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.cdb.mapper.ComputerDTOMapper;
import com.excilys.cdb.mapper.ComputerDto;
import com.excilys.cdb.page.Page;
import com.excilys.cdb.service.IServiceComputer;

@Controller
@RequestMapping({ "/Dashboard", "/" })
public class Dashboard {

	@Autowired
	private IServiceComputer servicecomputer;

	public Dashboard() {
		super();
	}

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(@ModelAttribute Page page, ModelMap model) {
		List<ComputerDto> lcomp = new ArrayList<ComputerDto>();
		page.validate();
		page.setNb(servicecomputer.getCountComputer(page.getSearch()));
		page.validatePageNum();
		page.turn();
		lcomp = ComputerDTOMapper.toListComputerDto(servicecomputer
				.findAllComputer(page));
		page.setLcomp(lcomp);
		model.addAttribute("page", page);
		return "dashboard";
	}
}
