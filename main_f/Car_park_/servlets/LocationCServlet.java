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
import sql.ILocationCConstants;

public class LocationCServlet extends GenericServlet{
	public void service(ServletRequest req,ServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		
		res.setContentType(IOnlineBookStoreConstants.CONTENT_TYPE_TEXT_HTML);
		
		
		int bDay=Integer.parseInt(req.getParameter(ILocationCConstants.COLUMN_DAY));
		
		String bMonth = req.getParameter(ILocationCConstants.COLUMN_MONTH);
		
		
		int bYear=Integer.parseInt(req.getParameter(ILocationCConstants.COLUMN_YEAR));
		
		
		String bIntime = req.getParameter(ILocationCConstants.COLUMN_INTIME);
		
		
		String bOutime = req.getParameter(ILocationCConstants.COLUMN_OUTTIME);
		
		
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("insert into " + ILocationCConstants.TABLE_LOCATIONC + "  values(?,?,?,?,?)");
			ps.setInt(1, bDay);
			ps.setString(2, bMonth);
			ps.setInt(3, bYear);
			ps.setString(4, bIntime);
			ps.setString(5, bOutime);
			
			int k = ps.executeUpdate();
			if(k==1)
			{
				RequestDispatcher rd = req.getRequestDispatcher("ConfirmationC.html");
				rd.include(req, res);
				pw.println("<span class=\"wsdf\">"+"Confirm your slot"+"</span>");

				
				
			}
			else
			{
				RequestDispatcher rd = req.getRequestDispatcher("UserBooking2c.html");
				pw.println("<div class=\"tab\">Failed to Add workers! Fill up CareFully</div>");
				rd.include(req, res);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

