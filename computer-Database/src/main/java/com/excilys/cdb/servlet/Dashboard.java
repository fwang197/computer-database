package com.excilys.cdb.servlet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.mapper.ComputerDTOMapper;
import com.excilys.cdb.page.Page;
import com.excilys.cdb.service.IServiceComputer;
import com.excilys.cdb.tools.Tools;

/**
 * Servlet implementation class DashboardServlet
 */

@Controller
@RequestMapping({ "/Dashboard", "/" })
public class Dashboard {

	@Autowired
	private IServiceComputer servicecomputer;

	public Dashboard() {
		super();
	}

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pageNum", required = false) String p,
			@RequestParam(value = "range", required = false) String srange,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "field", required = false) String field,
			ModelMap model) {
		int pageNum = 0;
		int range = 50;
		Page page = new Page();
		List<ComputerDto> lcomp = new ArrayList<ComputerDto>();

		if (Tools.isNumber(p)) {
			pageNum = Integer.parseInt(p);
		}
		if (Tools.isNumber(srange)) {
			range = Integer.parseInt(srange);
		}

		page.turn(pageNum, range);
		if (search == null || search.isEmpty()) {
			if (order == null || order.isEmpty()) {
				order = "";
				field = "computer.id";
			}

			lcomp = ComputerDTOMapper.toListComputerDto(servicecomputer
					.findAllComputer(page, field, order));
			search = "";
		} else {

			if (order == null || order.isEmpty()) {
				order = "";
				field = "computer.id";
			}

			lcomp = ComputerDTOMapper.toListComputerDto(servicecomputer
					.findAllComputer(page, search, field, order));
		}
		page.setNb(servicecomputer.getCountComputer(search));
		if (!page.validate()) {
			return "404";
		} else {
			page.setLcomp(lcomp);
			model.addAttribute("page", page);
			model.addAttribute("search", search);
			model.addAttribute("order", order);
			model.addAttribute("field", field);
			return "dashboard";
		}
	}
}
