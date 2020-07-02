package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;

@WebServlet("/inward")
public class Inward extends HttpServlet
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
			ServletContext ctx = getServletContext();
			try{
			Class.forName(ctx.getInitParameter("driver")).newInstance();
	        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
	        st=con.createStatement();
	        rs = st.executeQuery("SELECT * FROM inward_head ORDER BY transport_name");
	        List<List> rows = new ArrayList<List>();
	        while(rs.next())
	        {
	        	List cols=new ArrayList<String>();
	        	cols.add(rs.getString(1));
	        	cols.add(rs.getString(2));
	        	cols.add(rs.getString(3));
	        	cols.add(rs.getString(4));
	        	cols.add(rs.getString(5));
	        	cols.add(rs.getString(6));
	        	cols.add(rs.getString(7));
	        	cols.add(rs.getString(8));
	        	cols.add(rs.getString(9));
	        	cols.add(rs.getString(10));
	        	rows.add(cols);
	        } 
	        req.getSession().setAttribute("inwardhead", rows);

	        Set <String> engaged_inward = new HashSet<>();
	        st=con.createStatement();
	        rs=st.executeQuery("SELECT inward_id FROM inward_details");
	        while(rs.next())
	        {
	        	engaged_inward.add(rs.getString(1));
	        }
	        req.getSession().setAttribute("engaged_inward", engaged_inward);

			rd=req.getRequestDispatcher("inward_head.jsp");
			rd.forward(req,res);
		}catch(ClassNotFoundException e)
	    {
	    	res.getWriter().print("ClassNotFoundException !");
	    }
	    catch(SQLException e)
	    {
	    	res.getWriter().print("SQLException !");
	    }
	    catch(Exception e)
	    {
	    	res.getWriter().print("Exception");
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