package servlets;

import java.sql.*;
import javax.servlet.*;

import constants.IOnlineBookStoreConstants;
import sql.IUserContants;

import java.io.*;

public class UserRegisterServlet extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType(IOnlineBookStoreConstants.CONTENT_TYPE_TEXT_HTML);

		String uName = req.getParameter(IUserContants.COLUMN_USERNAME);
		String pWord = req.getParameter(IUserContants.COLUMN_PASSWORD);
		String mailId = req.getParameter(IUserContants.COLUMN_MAILID);
		//String usertype = req.getParameter(IUserContants.COLUMN_USERTYPE);
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con
					.prepareStatement("insert into " + IUserContants.TABLE_USERS + "  values(?,?,?,2)");
			ps.setString(1, uName);
			ps.setString(2, pWord);
			
			ps.setString(3, mailId);
//			ps.setString(8, usertype);
			//ps.setInt(8, 2);
			int k = ps.executeUpdate();
			if (k == 1) {
				RequestDispatcher rd = req.getRequestDispatcher("SuccessfulSignup.html");
				rd.include(req, res);
				pw.println("<h3 class='tab'>User Registered Successfully! Login again</h3>");
				pw.println("<button class=\"tab\"><br/>"
						+ "<a href=\"index.html\">Login page</a><br/>"
						+ "</button>");
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("userreg");
				rd.include(req, res);
				pw.println("Sorry for interruption! Register again");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}