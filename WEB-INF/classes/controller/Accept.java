package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.*;
import java.sql.*;
import java.util.*;

@WebServlet("/accept")

public class Accept extends HttpServlet
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
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int i = 9;
		try{
			Class.forName(ctx.getInitParameter("driver")).newInstance();
	        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));																	
	        st=con.createStatement();
	        rs=st.executeQuery("SELECT * FROM pending_user where user_name ='"+uname+"'" );
	        if(rs.next())
	        {
	        	String activity="TRUE";
	        	i=st.executeUpdate("INSERT INTO login_user (user_name,name,email,password,contact_number,activeuser) VALUES ('"+rs.getString(1)+"','"+rs.getString(2)+"','"+rs.getString(3)+"','"+rs.getString(4)+"','"+rs.getString(5)+"','"+activity+"')");
	        	st=null;
	        	st=con.createStatement();
	        	i=st.executeUpdate("DELETE FROM pending_user where user_name ='"+uname+"'" );
	        	RequestDispatcher rd=req.getRequestDispatcher("AcceptSuccess.jsp");
				rd.forward(req,res);
				
	        }
		}
		catch(SQLException e)
	    {
	    	res.getWriter().print("SQLException!");
	    	//RequestDispatcher rd=req.getRequestDispatcher("AcceptFailed.jsp");
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