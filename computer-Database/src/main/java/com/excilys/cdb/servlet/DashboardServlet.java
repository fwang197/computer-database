package com.excilys.cdb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.mapper.ComputerDTOMapper;
import com.excilys.cdb.page.Page;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.tools.Tools;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet(name = "DashboardServlet", urlPatterns = { "/DashboardServlet" })
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pageNum = 0;
		int range = 50;
		Page page = new Page();
		String search = request.getParameter("search");
		String p = request.getParameter("pageNum");
		String srange = request.getParameter("range");
		String order = request.getParameter("order");
		String field = request.getParameter("field");
		List<ComputerDto> lcomp = new ArrayList<ComputerDto>();

		if (Tools.isNumber(p)) {
			pageNum = Integer.parseInt(p);
		}
		if (Tools.isNumber(srange)) {
			range = Integer.parseInt(srange);
		}

		page.turn(pageNum, range);
		page.validate();

		if (search == null || search.isEmpty()) {
			if (order == null || order.isEmpty()) {
				order = "";
				field = "computer.id";
			}

			lcomp = ComputerDTOMapper
					.toListComputerDto(ServiceComputer.INSTANCE
							.findAllComputer(page, field, order));
			search = "";
		} else {

			if (order == null || order.isEmpty()) {
				order = "";
				field = "computer.id";
			}

			lcomp = ComputerDTOMapper
					.toListComputerDto(ServiceComputer.INSTANCE
							.findAllComputer(page, search, field, order));
		}
		page.setNb(ServiceComputer.INSTANCE.getCountComputer(search));
		page.setLcomp(lcomp);

		request.setAttribute("page", page);
		request.setAttribute("search", search);
		request.setAttribute("order", order);
		request.setAttribute("field", field);
		this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
