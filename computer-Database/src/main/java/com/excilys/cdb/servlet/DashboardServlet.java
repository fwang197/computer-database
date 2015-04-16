package main.java.com.excilys.cdb.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.service.ServiceComputer;
import main.java.com.excilys.cdb.tools.Tools;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArrayList<Computer> lcomp;
	private int nb;
	private int offset;
	private int range;
	private int pageNum;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardServlet() {
		super();
		nb = ServiceComputer.INSTANCE.getCountComputer();
		offset = 0;
		range = 50;
		pageNum = 0;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// On recupère la page de l'utilisateur
		String p = request.getParameter("pageNum");
		String move = request.getParameter("move");
		String srange = request.getParameter("range");
		if (Tools.isNumber(srange)) {
			range = Integer.parseInt(srange);
		}
		if (Tools.isNumber(p)) {
			pageNum = Integer.parseInt(p);
		}
		offset = pageNum * range;
		if (move != null) {
			if (move.equals("prev")) {
				offset -= range;
				if (offset < 0) {
					offset = 0;
				} else {
					pageNum--;
				}
			} else if (move.equals("next")) {
				if (offset + range < nb) {
					pageNum++;
				}
			}
		}
		System.out.println(pageNum);
		lcomp = new ArrayList<Computer>(
				ServiceComputer.INSTANCE.findAllRangeComputer(offset, range));
		request.setAttribute("list", lcomp);
		request.setAttribute("nb", nb);
		request.setAttribute("offset", offset);
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
