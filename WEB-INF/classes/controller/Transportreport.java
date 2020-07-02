package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;

@WebServlet("/Transportreport")
public class Transportreport extends HttpServlet
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

	        String from_date = req.getParameter("from_date");
	        String to_date=req.getParameter("to_date");
	        st=con.createStatement();
	        rs=st.executeQuery("SELECT * FROM inward_head WHERE arrival_date>='"+from_date+"'and arrival_date<='"+to_date+"'");
	        //res.getWriter().print("that!");

	        ArrayList rows=new ArrayList<List>();
	        while(rs.next())
	        {
	        	List cols=new ArrayList<String>();
	        	cols.add(rs.getString(2));
	        	cols.add(rs.getString(3));
	        	//not coming
	        	cols.add(rs.getString(5));

	        	cols.add(rs.getString(7));

	        	cols.add(rs.getString(8));

	        	cols.add(rs.getString(9));
	        	rows.add(cols);
	        }
	        session.setAttribute("fr",from_date);
	        session.setAttribute("tr",to_date);

	        req.getSession().setAttribute("transport", rows);	
			rd=req.getRequestDispatcher("transport_report.jsp");
			rd.forward(req,res);
		}catch(ClassNotFoundException e)
	    {
	    	res.getWriter().print("ClassNotFoundException !");
	    }
	    catch(SQLException e)
	    {
	    	res.getWriter().print("SQLException!");
	        rd.forward(req,res);
	    }
	    /*catch(Exception e)
	    {
	    	res.getWriter().print("Exception");
	    }*/
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
		