package com.placementswebsite;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			response.setContentType("text/html");
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("helo1");
			String url = "jdbc:mysql://localhost:3307/userregistrationdb";
			Connection con = DriverManager.getConnection(url, "root", "12345");
			String firstname = request.getParameter("fname");
			String lastname = request.getParameter("lname");
			String DOB = request.getParameter("dob");
			String username = request.getParameter("uname");
			String password = request.getParameter("pwd");
			String query = "insert into registrationtable values(?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, firstname);
			pstmt.setString(2, lastname);
			pstmt.setString(3, DOB);
			pstmt.setString(4, username);
			pstmt.setString(5, password);
			int i = pstmt.executeUpdate();
			if (i > 0) {
				out.print("You are successfully registered");
				response.sendRedirect("Login.html");  
			} else {
				out.print("something went wrong");
			}

		} catch (SQLException e) {

		    out.print(" \t username already exixts , please choose another name");
		    request.getRequestDispatcher("Registration.html").include(request, response);

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}
