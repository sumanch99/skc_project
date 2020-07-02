package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;

@WebServlet("/updatethisinwarddetails")
public class UpdateThisInwardDetails extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		HttpSession session = req.getSession();
		if(session.getAttribute("user_name")==null)
		{
			RequestDispatcher rd=req.getRequestDispatcher("userlogin");
			rd.forward(req,res);
		}
		else
		{
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			ServletContext ctx = getServletContext();
			try{
			Class.forName(ctx.getInitParameter("driver")).newInstance();
	        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
	        st=con.createStatement();
	        String inwarddetailsid = req.getParameter("inwarddetailsid");
	        String c_code = req.getParameter("c_code");
	        String c_bill_no = req.getParameter("c_bill_no");
	        String c_bill_date = req.getParameter("c_bill_date");
	        rs=st.executeQuery("SELECT consigner_id FROM consigner_master WHERE consigner_code='"+c_code+"'");
	        rs.next();
	        String c_id = rs.getString(1);
	        st=con.createStatement();
	        int i=st.executeUpdate("UPDATE inward_details SET consigner_id='"+c_id+"',bill_no='"+c_bill_no+"',bill_date='"+c_bill_date+"' WHERE inward_details_id='"+inwarddetailsid+"'");
            RequestDispatcher rd=req.getRequestDispatcher("UpdateInwardDetailsSuccess.jsp");
			rd.forward(req,res);
	        }catch(ClassNotFoundException e)
		    {
		    	res.getWriter().print("ClassNotFoundException !");
		    }
		    catch(SQLException e)
		    {
		    	res.getWriter().print("SQLException!");
		       
		    }
		    catch(InstantiationException e)
		    {
		    	res.getWriter().print("InstantiationException");
		    }
		    catch(IllegalAccessException e)
		    {
		    	res.getWriter().print("IllegalAccessException");
		    }
		    finally{
			    	try{
			    		if (rs!=null) 
			    			rs.close();
			    		if (st!=null) 
			    			st.close();
			    		if (con!=null) 
			    			con.close();
			    	}catch(Exception e)
			    	{
			    		res.getWriter().print("Fatal Error Occured!");
			    	}		
		      	}
		}
	}	
}