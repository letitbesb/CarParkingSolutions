package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import constants.IOnlineBookStoreConstants;
import sql.ISpaceConstants;

public class UpdateSpace2Servlet extends GenericServlet{
	public void service(ServletRequest req,ServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		
		res.setContentType(IOnlineBookStoreConstants.CONTENT_TYPE_TEXT_HTML);
		
		String bCode = req.getParameter(ISpaceConstants.COLUMN_ADDRESS);
		int bPrice =Integer.parseInt(req.getParameter(ISpaceConstants.COLUMN_NSPACES));
		
		
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("insert into " + ISpaceConstants.TABLE_SPACES + "  values(?,?)");
			ps.setString(1, bCode);
			ps.setInt(2 ,bPrice);
			
			int k = ps.executeUpdate();
			if(k==1)
			{
				RequestDispatcher rd = req.getRequestDispatcher("UpdateSpace2.html");
				rd.include(req, res);
				pw.println("<span class=\"wsdf\">"+"Update successful"+"</span>");
				pw.println("<button>"
						+ "<br/><a href=\"viewspace\">View spaces</a></button>");
				
			}
			else
			{
				RequestDispatcher rd = req.getRequestDispatcher("UpdateSpace2.html");
				pw.println("<div class=\"tab\">Failed to Update! </div>");
				rd.include(req, res);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
