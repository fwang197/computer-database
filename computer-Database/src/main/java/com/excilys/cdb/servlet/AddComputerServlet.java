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
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ServiceCompany;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.tools.Tools;
import com.excilys.cdb.tools.Validator;

/**
 * Servlet implementation class AddComputerServlet
 */
@Controller
@WebServlet("/AddComputerServlet")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ServiceCompany servicecompany;

	@Autowired
	private ServiceComputer servicecomputer;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputerServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Company> lcomp = new ArrayList<Company>(
				servicecompany.findAllCompany());
		request.setAttribute("list", lcomp);
		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/addComputer.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("computerName");
		String intro = request.getParameter("introduced");
		String discon = request.getParameter("discontinued");
		String id = request.getParameter("companyId");

		ArrayList<Company> lcomp = new ArrayList<Company>(
				servicecompany.findAllCompany());
		request.setAttribute("list", lcomp);

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
			System.out.println("OK");

			response.sendRedirect("DashboardServlet");
		} else {
			request.setAttribute("list", lcomp);
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/addComputer.jsp")
					.forward(request, response);
		}
	}
}
