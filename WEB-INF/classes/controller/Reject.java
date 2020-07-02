package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.*;
import java.sql.*;
import java.util.*;

@WebServlet("/reject")

public class Reject extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		HttpSession session = req.getSession();
		if(session.getAttribute("admin_user_name")==null)
		{
			RequestDispatcher rd=req.getRequestDispatcher("adminlogin");
			rd.forward(req,res);
		}
		ServletContext ctx = getServletContext();
		String uname=req.getParameter("username");
		if(uname==null)
		{
			RequestDispatcher rd=req.getRequestDispatcher("adminlogin");
			rd.forward(req,res);
		}
		//res.getWriter().print(uname);
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int i=9;
		try{
			Class.forName(ctx.getInitParameter("driver")).newInstance();
	        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));																	
	        st=con.createStatement();
	        i=st.executeUpdate("DELETE FROM pending_user WHERE user_name ='"+uname+"'");
	        //res.getWriter().print(i+uname);
	        RequestDispatcher rd=req.getRequestDispatcher("RejectSuccess.jsp");
			rd.forward(req,res);
		}
		catch(SQLException e)
	    {
	    	res.getWriter().print("SQLException!");
	    	//RequestDispatcher rd=req.getRequestDispatcher("RejectFailed.jsp");
			//rd.forward(req,res);
	    }
	    catch(ClassNotFoundException e)
	    {
	    	res.getWriter().print("ClassNotFoundException!");
	    }
	    catch(InstantiationException e)
	    {
	    	res.getWriter().print("InstantiationException!");
	    }
	    catch(IllegalAccessException e)
	    {
	    	res.getWriter().print("IllegalAccessException!");
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