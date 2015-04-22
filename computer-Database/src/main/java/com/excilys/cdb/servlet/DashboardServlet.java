package com.excilys.cdb.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.mapper.ComputerDTOMapper;
import com.excilys.cdb.model.Computer;
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
		int nb = 0;
		int offset = 0;
		int pageNum = 0;
		int range = 50;
		String search = request.getParameter("search");
		String p = request.getParameter("pageNum");
		String srange = request.getParameter("range");

		ArrayList<ComputerDto> lcomp = new ArrayList<ComputerDto>();

		if (Tools.isNumber(p)) {
			pageNum = Integer.parseInt(p);
		}
		if (Tools.isNumber(srange)) {
			range = Integer.parseInt(srange);
		}

		offset = pageNum * range;
		if (offset < 0) {
			offset = 0;
		}

		if (search == null || search.equals("")) {
			for (Computer c : ServiceComputer.INSTANCE.findAllRangeComputer(
					offset, range)) {
				lcomp.add(ComputerDTOMapper.toComputerDto(c));
			}
			nb = ServiceComputer.INSTANCE.getCountComputer();
		} else {
			for (Computer c : ServiceComputer.INSTANCE
					.findAllRangePatternComputer(offset, range, search)) {
				lcomp.add(ComputerDTOMapper.toComputerDto(c));
			}
			nb = ServiceComputer.INSTANCE.getCountPatternComputer(search);
		}

		System.out.println("offset " + offset);
		System.out.println("range " + range);
		System.out.println("pageNum " + pageNum);
		System.out.println();

		request.setAttribute("list", lcomp);
		request.setAttribute("nb", nb);
		request.setAttribute("range", range);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("search", search);
		this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
