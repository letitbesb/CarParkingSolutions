package servlets;

import java.sql.*;
import javax.servlet.*;

import sql.ISpaceConstants;

import java.io.*;

public class UpdateSpaceServlet extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String bkidd = req.getParameter("address");
		
		
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"delete from " + ISpaceConstants.TABLE_SPACES + "  where " + ISpaceConstants.COLUMN_ADDRESS + "=?");
			//update IncrementAndDecrementValue set UserScores = UserScores-1 where UserId = 103;
			ps.setString(1, bkidd);
			
			int k = ps.executeUpdate();
			if (k == 1) {
				RequestDispatcher rd = req.getRequestDispatcher("UpdateSpace2.html");
				rd.include(req, res);
				

			} else {
				RequestDispatcher rd = req.getRequestDispatcher("UpdateSpace2.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Space Not Available In The Store</div>");
				pw.println("<div class=\"tab\"><a href=\"RemoveSpace.html\">Remove more Spaces</a></div>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
// THIS SERVLET IS FOR REMOVAL/DELETION FIRST