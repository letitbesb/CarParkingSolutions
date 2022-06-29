package servlets;

import java.sql.*;
import java.util.Random;
import java.io.*;
import javax.servlet.*;

import constants.IOnlineBookStoreConstants;

import sql.ISpaceConstants;



public class ReceiptC2Servlet extends GenericServlet {
	
public String getRandom() {
	Random rnd=new Random();
	int number= rnd.nextInt(999999);
	return String.format("%06d", number);
}
	
	
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		
		PrintWriter pw = res.getWriter();
		res.setContentType(IOnlineBookStoreConstants.CONTENT_TYPE_TEXT_HTML);
		
	
//		String bIntime= req.getParameter(ILocationAConstants.COLUMN_INTIME);
//		String bOutime = req.getParameter(ILocationAConstants.COLUMN_OUTTIME);
		try {
			
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("Select * from " + ISpaceConstants.TABLE_SPACES );
			ResultSet rs = ps.executeQuery();
			RequestDispatcher rd = req.getRequestDispatcher("Finalconfirmation.html");
			rd.include(req, res);
			
			if (rs.last()) {
				
				int Nspaces= rs.getInt(ISpaceConstants.COLUMN_NSPACES);
			
				Nspaces=Nspaces-1;
				
				if(Nspaces>-1) {
					pw.println("<div class=\"tab hd brown\">Your booking is confirmed.</div><br/>");
					pw.println("<div class=\"tab hd brown\">Your reference number is: "+getRandom()+"</div><br/>");
					PreparedStatement ps1 = con.prepareStatement("update " + ISpaceConstants.TABLE_SPACES + " set "
							+ ISpaceConstants.COLUMN_NSPACES + "=? where " + ISpaceConstants.COLUMN_ADDRESS + "=?");
					ps1.setInt(1,Nspaces);
					ps1.setString(2,"C");
					ps1.executeUpdate();
					}
					else {
						pw.println("<div class=\"WERTT\">The space is full for this particular slot.</div><br/>");
						pw.println("<div class=\"WERTT\">You have been put in waiting list with reference number "+getRandom()+"</div><br/>");
					}
	
//				pw.println("<div class=\"tab hd brown\">Booking Confirmed</div><br/>");
				
				
//				pw.println("<button class=\"tab\">"
//						+ "<br/><a href=\"UpdateSpace.html\">Confirm Booking</a><br/>"
//						+ "</button>");

			} 

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
//		try {
//			Connection con = DBConnection.getCon();
//			PreparedStatement ps = con.prepareStatement("select * from " + ILocationAConstants.TABLE_LOCATIONA );
//		
//			
//			ResultSet rs = ps.executeQuery();
//
//			int i = 0;
//			RequestDispatcher rd = req.getRequestDispatcher("Receipt1a.html");
//			rd.include(req, res);
//			pw.println("<div class=\"tab\">You Successfully Paid for Following Books</div>");
//			
//			double total = 0.0;
//			while (rs.next()) {
//			
//		
//			
//			
//			
//				try {
//					
//					int bIntimeMins=MinutesConverter(bIntime);
//					int bOutimeMins=MinutesConverter(bOutime);
//					int amount =((Math.abs(bOutimeMins-bIntimeMins))/60)* 25;
//					
//						total = total + amount;
//				
//						
//				} catch (Exception e) {
//				}}
//			
//			pw.println("</table><br/><div class='tab'>Total Paid Amount: " + total + "</div>");
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}
