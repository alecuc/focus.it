package it.focus.controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.focus.model.UserBean;
import it.focus.model.UserBeanDAO;

/**
 * Servlet implementation class manageUsers
 */
@WebServlet("/manageUsers")
public class manageUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public manageUsers() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("-----------------------");
		System.out.println("Inizio metodo: doGet - Servlet: manageUsers");
		System.out.println("-----------------------");

		HttpSession session= request.getSession();
		UserBean userB= (UserBean) session.getAttribute("userBean");

		if (userB== null) {
			RequestDispatcher reqDis= request.getRequestDispatcher("./servicepage/nopermission.jsp");
			reqDis.forward(request, response);
		}
		else {
			try {
				UserBeanDAO ubDAO= new UserBeanDAO();


				

			} 
			catch (Exception e) {
				RequestDispatcher reqDis= request.getRequestDispatcher("./servicepage/exception.jsp");
				reqDis.forward(request, response);
			}

		}	





		System.out.println("-----------------------");
		System.out.println("Fine metodo: doGet - Servlet: manageUsers");
		System.out.println("-----------------------");



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
