package servlets;

import java.sql.*;
import javax.servlet.*;

import sql.ISpaceConstants;

import java.io.*;

public class RemoveSpaceServlet extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String bkiddd = req.getParameter("address");
		
		int bnspace =Integer.parseInt(req.getParameter("nspaces"));
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"delete from " + ISpaceConstants.TABLE_SPACES + "  where " + ISpaceConstants.COLUMN_ADDRESS + "=? AND" + ISpaceConstants.COLUMN_NSPACES + "=?");
			//update IncrementAndDecrementValue set UserScores = UserScores-1 where UserId = 103;
			ps.setString(1, bkiddd);
			ps.setInt(2, bnspace);
			int k = ps.executeUpdate();
			if (k == 1) {
				RequestDispatcher rd = req.getRequestDispatcher("RemoveSpace2.html");
				rd.include(req, res);
				pw.println("<div class=\"tggbn\">Space Removed Successfully</div>");
				pw.println("<button class\"cdvd\"> <a href=\"RemoveSpace.html\">Remove more Spaces</a></button>");

			} else {
				RequestDispatcher rd = req.getRequestDispatcher("RemoveSpace2.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Space Not Available In The Store</div>");
				pw.println("<div class=\"tab\"><a href=\"RemoveSpace.html\">Remove more Spaces</a></div>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
