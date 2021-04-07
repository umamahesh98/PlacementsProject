package com.placementswebsite;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		if (session != null) {
			session.invalidate();
			 	out.print("you are successfully logged out!");
			request.getRequestDispatcher("Logout.jsp").include(request, response);
			//response.sendRedirect("logout.jsp");
		} else {
			out.print("Please login first");
			request.getRequestDispatcher("Login.html").include(request, response);
		}
		
	}

	
	

}
