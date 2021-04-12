package com.placementswebsite;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		ServletContext context=getServletContext();
		String name =(String) context.getAttribute("name");
		HttpSession session = request.getSession(false);
		if (session != null) {
			response.setContentType("text/html");
			out.println("<html><body>");
			String url = "jdbc:mysql://localhost:3307";
			String query = "select * from userregistrationdb.registrationtable where username=?";
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(url, "root", "12345");
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setString(1, name);
				
				ResultSet rs = stmt.executeQuery();
				out.println("<table border=2px solid green>");
				out.println("<tr><th>firstname</th><th>lastname</th><th>DOB</th><th>username</th><th>password</th></tr>");
				while (rs.next()) {
					
					String firstname1 = rs.getString("firstname");
					String lastname1 = rs.getString("lastname");
					String DOB1 = rs.getString("DOB");
					String username1 = rs.getString("username");
					String password1 = rs.getString("password");
					out.println("<tr><td>" + firstname1 + "</td><td>" + lastname1 + "</td><td>" + DOB1 + "</td><td>"
							+ username1 + "</td><td>" + password1 + "</td></tr>");
				}
				out.println("</table>");
				out.println("</html></body>");
				con.close();

			} catch (Exception e) {
				out.println("error");
			}
		} else {
			out.print("Please login first");
			response.sendRedirect("Login.html");
		}
	}
}
