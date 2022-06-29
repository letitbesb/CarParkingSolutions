package servlets;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import constants.IOnlineBookStoreConstants;
import sql.IUserContants;

import java.io.*;
import java.sql.*;

public class AdminLoginServlet extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType(IOnlineBookStoreConstants.CONTENT_TYPE_TEXT_HTML);
		String uName = req.getParameter(IUserContants.COLUMN_USERNAME);
		String pWord = req.getParameter(IUserContants.COLUMN_PASSWORD);
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + IUserContants.TABLE_USERS + " WHERE  "
					+ IUserContants.COLUMN_USERNAME + "=? AND " + IUserContants.COLUMN_PASSWORD + "=? AND "
					+ IUserContants.COLUMN_USERTYPE + "=1");
			ps.setString(1, uName);
			ps.setString(2, pWord);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				RequestDispatcher rd = req.getRequestDispatcher("AdminDashboard.html");
				
				Cookie ck = new Cookie("usertype","admin");
				
				rd.include(req, res);
//				pw.println("<div class=\"tab\">Admin login Successful</div>");
//				pw.println("<button class=\"tab\">"
//						+ "<br/><a href=\"AddBook.html\">Add Workers</a><br/>"
//						+ "</button>");
//				pw.println("<button class=\"tab\"><br/>"
//						+ "<a href=\"RemoveBooks.html\">Remove Workers</a><br/>"
//						+ "</button>");
//				pw.println("<button class=\"tab\">"
//						+ "<br/><a href=\"viewbook\">View workers</a></button>");
//				pw.println("<button class=\"tab\">"
//						+ "<br/><a href=\"AddSpace.html\">Add slots</a><br/>"
//						+ "</button>");
//				pw.println("<button class=\"tab\">"
//						+ "<br/><a href=\"UpdateSpace.html\">Update slots</a><br/>"
//						+ "</button>");
			} else {

				RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Incorrect UserName or PassWord</div>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}