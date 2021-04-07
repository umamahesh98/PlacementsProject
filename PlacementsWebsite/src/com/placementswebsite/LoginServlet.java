package com.placementswebsite;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html;charset=UTF-8");
			Class.forName("com.mysql.jdbc.Driver");	
			PrintWriter out = response.getWriter();
			String url = "jdbc:mysql://localhost:3307/userregistrationdb";
			Connection con = DriverManager.getConnection(url, "root", "12345");
			String username = request.getParameter("uname");
			String password = request.getParameter("pword");
			String query = "select * from registrationtable where username =? && password =?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("Your are successfully logined");
				HttpSession session = request.getSession();
				session.setAttribute("user", username);
				// session.setMaxInactiveInterval(5);
				request.getRequestDispatcher("Index.html").include(request, response);
			} else {

				out.println("\t Invalid credentials");
				request.getRequestDispatcher("Login.html").include(request, response);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
