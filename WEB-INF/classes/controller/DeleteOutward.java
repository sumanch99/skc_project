package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;
import java.time.*;

@WebServlet("/deleteoutward")
public class DeleteOutward extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		RequestDispatcher rd=null;
		HttpSession session = req.getSession();
		if(session.getAttribute("user_name")==null)
		{
			rd=req.getRequestDispatcher("userlogin");
			rd.forward(req,res);
		}

		else
		{
		try{
			ServletContext ctx = getServletContext();
			Class.forName(ctx.getInitParameter("driver")).newInstance();
			con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
			st=con.createStatement();
			String inward_lr_details_id = req.getParameter("inward_lr_details_id");
			st.executeUpdate("DELETE FROM outward_bales WHERE inward_lr_details_id='"+inward_lr_details_id+"'");	
			st=con.createStatement();
			st.executeUpdate("UPDATE inward_lr_details SET outward_flag='"+false+"' WHERE inward_lr_details_id='"+inward_lr_details_id+"'");
			rd=req.getRequestDispatcher("d_cartdeletesuccess.jsp");
			rd.forward(req,res);
		}catch(ClassNotFoundException e)
	    {
	    	res.getWriter().print("ClassNotFoundException !");
	    }
	    catch(SQLException e)
	    {
	    	res.getWriter().print("SQLException !");
	    }
	    catch(IllegalAccessException e)
	    {
	    	res.getWriter().print("IllegalAccessException");
	    }
	    catch(InstantiationException e)
	    {
	    	res.getWriter().print("InstantiationException !");
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