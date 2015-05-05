package com.excilys.cdb.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.cdb.dao.DateMapper;
import com.excilys.cdb.mapper.ComputerDTOMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ServiceCompany;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.tools.Tools;
import com.excilys.cdb.tools.Validator;

/**
 * Servlet implementation class EditComputerServlet
 */
@Controller
@WebServlet("/EditComputerServlet")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ServiceComputer servicecomputer;

	@Autowired
	private ServiceCompany servicecompany;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditComputerServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter("compId");
		long id = 0;
		if (Tools.isNumber(sid)) {
			id = Long.parseLong(sid);
			if (servicecomputer.findComputer(id) == null) {
				id = 1;
			}
		}

		ArrayList<Company> lcomp = new ArrayList<Company>(
				servicecompany.findAllCompany());
		request.setAttribute("list", lcomp);

		ComputerDto comp = ComputerDTOMapper.toComputerDto(servicecomputer
				.findComputer(id));
		request.setAttribute("compId", comp.getId());
		request.setAttribute("name", comp.getName());
		request.setAttribute("intro", comp.getIntroduced());
		request.setAttribute("discon", comp.getDiscontinued());
		request.setAttribute("compName", comp.getCompanyName());
		request.setAttribute("companyId", comp.getCompanyId());
		request.setAttribute("list", lcomp);
		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/editComputer.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String scompanyid = request.getParameter("companyId");
		String scomputerid = request.getParameter("compId");

		ArrayList<Company> lcomp = new ArrayList<Company>(
				servicecompany.findAllCompany());
		request.setAttribute("list", lcomp);

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
				System.out.println("OK");
				response.sendRedirect("DashboardServlet");
			}
		} else {
			request.setAttribute("compId", Long.parseLong(scomputerid));
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/editComputer.jsp")
					.forward(request, response);
		}
	}
}
