package it.lucaamoriello.focusit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;

import it.lucaamoriello.focusit.model.CartBean;
import it.lucaamoriello.focusit.model.CartBeanDAO;
import it.lucaamoriello.focusit.model.UserBean;
import it.lucaamoriello.focusit.model.UserBeanDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		System.out.println("-----------------------");
		System.out.println("Inizio metodo: doGet - Servlet: Login");
		System.out.println("-----------------------");
		
				UserBean userB = getUserPassword(request);

				if (userB == null) {
					
					request.setAttribute("nodata", true);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("./singinup.jsp");
					requestDispatcher.forward(request, response);
				}
				else {
					try{
						UserBeanDAO ubd = new UserBeanDAO();
						CartBean cart = new CartBean();
						CartBeanDAO cbd = new CartBeanDAO();

						cart = cbd.restore(userB.getUsr());
						if(cart.getTotal()==0)
							cart = new CartBean();


						UserBean ub = ubd.doRetriveByKey(userB.getUsr(), userB.getPwd());

						HttpSession session = request.getSession();

						if (ub==null) {

							request.setAttribute("denied", true);
							RequestDispatcher requestDispatcher = request.getRequestDispatcher("./singinup.jsp");
							requestDispatcher.forward(request, response);
						}else {

							Cookie usrcookie = new Cookie("usr", ub.getUsr());
							Cookie namecookie = new Cookie("name", ub.getNome());
							Cookie surnamecookie = new Cookie("surname", ub.getCognome());
							Cookie rolecookie = new Cookie("role", ub.getRole());
							Cookie emailcookie = new Cookie("email", ub.getEmail());
							Cookie phonecookie = new Cookie("phone", ub.getPhone());



							usrcookie.setMaxAge(60*60*24);
							surnamecookie.setMaxAge(60*60*24);
							usrcookie.setMaxAge(60*60*24);
							emailcookie.setMaxAge(60*60*24);
							phonecookie.setMaxAge(60*60*24);
							rolecookie.setMaxAge(60*60*24);

							emailcookie.setPath("/");
							phonecookie.setPath("/");
							surnamecookie.setPath("/");
							namecookie.setPath("/");
							usrcookie.setPath("/");
							rolecookie.setPath("/");

							response.addCookie(surnamecookie);
							response.addCookie(usrcookie);
							response.addCookie(emailcookie);
							response.addCookie(phonecookie);
							response.addCookie(rolecookie);
							response.addCookie(namecookie);
							
							cart.setUser(ub.getUsr());
							session.setAttribute("cart", cart);
							session.setAttribute("userBean", ub);
							session.setMaxInactiveInterval(60*60*24);

//							testing purpose
							System.out.println("Nome: " + ub.getNome());

							System.out.println("Cart: " + cart.isEmpty());



							RequestDispatcher requestDispatcher = request.getRequestDispatcher("./servicepage/successLogin.jsp");
							requestDispatcher.forward(request, response);
							
							System.out.println("-----------------------");
							System.out.println("Fine metodo: doGet - Servlet: Login");
							System.out.println("-----------------------");
						}
					}
					catch(Exception e)
					{
						request.setAttribute("exception", e);
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("./servicepage/exception.jsp");
						requestDispatcher.forward(request, response);
					}
				}
			}



	private UserBean getUserPassword(HttpServletRequest request) {
		UserBean ub = null;
		String usr = null, pwd = null;

		Cookie[] c = request.getCookies();
		if(c!=null) {
			for(int i=0; i<c.length;i++) {
				if (c[i].getName().equals("usr"))
					usr = c[i].getValue();
				if(c[i].getName().equals("psw"))
					pwd = c[i].getValue();
			}
		}
		if (usr == null || pwd == null) {
			String tmp;
			tmp = request.getParameter("user");
			if(tmp!=null) {
				usr=tmp;
			tmp=request.getParameter("psw");
			if(tmp!=null)
				pwd=tmp;
			}
		}

		if(usr!=null && pwd!=null)
			ub = new UserBean(usr, pwd);

		return ub;

		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
}


}
