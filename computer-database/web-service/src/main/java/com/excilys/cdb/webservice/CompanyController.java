package com.excilys.cdb.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.ICompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private ICompanyService companyService;

	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public Company getCompany(@RequestParam(value = "id") long id) {
		Company c = companyService.findCompany(id);
		return c;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getAll")
	public Object[] getAll() {
		return companyService.findAllCompany().toArray();
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/delete")
	public void deleteCompany(@RequestParam(value = "id") long id) {
		Company c = companyService.findCompany(id);
		companyService.deleteCompany(c);
	}

}
