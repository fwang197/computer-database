package main.java.com.excilys.cdb.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.excilys.cdb.mapper.Mapper;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.service.ServiceCompany;
import main.java.com.excilys.cdb.service.ServiceComputer;
import main.java.com.excilys.cdb.tools.Tools;

/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet("/AddComputerServlet")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArrayList<Company> lcomp;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputerServlet() {
		super();
		lcomp = new ArrayList<Company>(ServiceCompany.INSTANCE.findAllCompany());
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
		System.out.println(name + " " + intro + " " + discon + " " + id);
		if (!name.equals("")) {
			Company comp = null;
			if (Tools.isNumber(id)) {
				comp = ServiceCompany.INSTANCE.findCompany(Long.parseLong(id));
			}
			ServiceComputer.INSTANCE.createComputer(new Computer(0, name,
					Mapper.toDateFormat(intro), Mapper.toDateFormat(discon),
					comp));
			System.out.println("OK");

			response.sendRedirect("DashboardServlet?reload=yes");
		} else {
			request.setAttribute("list", lcomp);
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/addComputer.jsp")
					.forward(request, response);
		}
	}
}
