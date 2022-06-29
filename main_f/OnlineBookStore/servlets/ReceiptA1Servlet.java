package servlets;

import java.sql.*;
import java.util.StringTokenizer;
import java.io.*;
import javax.servlet.*;
import java.lang.Math;
import constants.IOnlineBookStoreConstants;
import sql.IBookConstants;
import sql.ILocationAConstants;
import sql.IUserContants;


public class ReceiptA1Servlet extends GenericServlet {
	
	
	public double MinutesConverter(String date) {
		int k=0;
	       
	    StringTokenizer st = new StringTokenizer(date,":");
	    String[] parts=new String[2];
	    if (date.contains(":")==true)
	    {
	        while(st.hasMoreTokens()) 
	        {
	             parts[k]=st.nextToken();
	             k++;
	        }
	    }
	    int p1=Integer.parseInt(parts[0]);
	    int p2=Integer.parseInt(parts[1]);
	    int p3=(p1*60) + p2;
	    Double d2=Double.valueOf(p3);
	    Double d3= Math.floor(d2*100)/100;
		return d3;
	}
	
	
	
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType(IOnlineBookStoreConstants.CONTENT_TYPE_TEXT_HTML);
		String bIntime= req.getParameter(ILocationAConstants.COLUMN_INTIME);
		String bOutime = req.getParameter(ILocationAConstants.COLUMN_OUTTIME);
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + ILocationAConstants.TABLE_LOCATIONA + " WHERE "
					+ ILocationAConstants.COLUMN_INTIME + "=? AND " + ILocationAConstants.COLUMN_OUTTIME + "=?");
			ps.setString(1, bIntime);
			ps.setString(2, bOutime);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				double total = 0.0;
				double bIntimeMins=MinutesConverter(bIntime);
				double bOutimeMins=MinutesConverter(bOutime);
				double amount =((bOutimeMins-bIntimeMins)/60)* 25;
				
					total = total + amount;
			
					
				RequestDispatcher rd = req.getRequestDispatcher("Receipt1a.html");
				rd.include(req, res);
				
				;
				pw.println("<br/><div class='WERTT'>Total Amount to be paid: Rs. " + total + "</div>");
				pw.println("<button>"
						+ "<br/><a href=\"receiptatwo\">Confirm Booking</a></button>");
				
//				pw.println("<button class=\"tab\">"
//						+ "<br/><a href=\"UpdateSpace.html\">Confirm Booking</a><br/>"
//						+ "</button>");
		
			
			
			} else {

				RequestDispatcher rd = req.getRequestDispatcher("ConfirmationA.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Incorrect UserName or PassWord</div>");
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
