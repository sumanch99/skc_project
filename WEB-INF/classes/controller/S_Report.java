package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.*;
import java.sql.*;

@WebServlet("/S_Report")
public class S_Report extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ServletContext ctx = getServletContext();
		RequestDispatcher rd=null;
		HttpSession session = req.getSession();
		int flag;
		if(session.getAttribute("user_name")==null)
		{
			rd=req.getRequestDispatcher("userlogin");
			rd.forward(req,res);
		}
		try{

		}
		catch(ClassNotFoundException e)
		{
		    res.getWriter().print("ClassNotFoundException !");
		}
		catch(SQLException e)
		{
		    res.getWriter().print("SQLException!");
		    rd.forward(req,res);
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