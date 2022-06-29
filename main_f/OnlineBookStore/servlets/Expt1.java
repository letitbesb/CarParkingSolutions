package servlets;
import java.io.*;
import java.sql.*;
import javax.servlet.*;

import constants.IOnlineBookStoreConstants;
import sql.ISpaceConstants;

public class Expt1 extends GenericServlet{
	public void service(ServletRequest req,ServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("Select * from " + ISpaceConstants.TABLE_SPACES);
			ResultSet rs = ps.executeQuery();
			RequestDispatcher rd = req.getRequestDispatcher("ViewSpace.html");
			rd.include(req, res);
			pw.println("<div class=\"tab\">Spaces Available </div>");
			pw.println("<div class=\"tab\">\r\n" + 
					"		<table id =\"customers\">\r\n" + 
					"			<tr>\r\n" + 
					"				\r\n" + 
					"				<th>Address</th>\r\n" + 
					"				<th>Number of Spaces</th>\r\n" +
					
					"			</tr>");
			
			
				String Add = rs.getString(1);
				
				String Nspaces = rs.getString(2);
				pw.println("<tr><td>"+Add+"</td>");

				pw.println("<td>"+ Nspaces+"</td>");
				
			
			pw.println("</table>\r\n" + 
					"	</div>");
			//pw.println("<div class=\"tab\"><a href=\"AddBook.html\">Add More Books</a></div>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

