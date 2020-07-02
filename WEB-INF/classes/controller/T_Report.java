package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.*;
import java.sql.*;

@WebServlet("/T_Report")
public class T_Report extends HttpServlet
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
		if(req.getParameter("from_date")==null && req.getParameter("to_date")==null)
		{
			flag=0;
			session.setAttribute("flag",flag);
			rd=req.getRequestDispatcher("T_Report.jsp");
			rd.forward(req,res);
		}
		else{
			try{
				String from_date = req.getParameter("from_date");
				String to_date = req.getParameter("to_date");
				if(req.getParameter("from_date").equals("") && req.getParameter("to_date").equals(""))
				{
					flag=1;
					//res.getWriter().print("Got Nothing");
					Class.forName(ctx.getInitParameter("driver")).newInstance();
	        		con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
	        		st=con.createStatement();
	        		rs=st.executeQuery("SELECT * FROM inward_head");
				}
				else if(req.getParameter("from_date").equals(""))
				{
					flag=1;
					//res.getWriter().print("Got to_date");
					Class.forName(ctx.getInitParameter("driver")).newInstance();
	        		con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
	        		st=con.createStatement();
	        		rs=st.executeQuery("SELECT * FROM inward_head WHERE arrival_date<='"+to_date+"'");
				}
				else if(req.getParameter("to_date").equals(""))
				{
					flag=1;
					//res.getWriter().print("Got from_date");
					Class.forName(ctx.getInitParameter("driver")).newInstance();
	        		con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
	        		st=con.createStatement();
	        		rs=st.executeQuery("SELECT * FROM inward_head WHERE arrival_date>='"+from_date+"'");
				}
				else{
					flag=1;
					//res.getWriter().print("Got Both");
					Class.forName(ctx.getInitParameter("driver")).newInstance();
	        		con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
	        		st=con.createStatement();
	        		rs=st.executeQuery("SELECT * FROM inward_head WHERE arrival_date>='"+from_date+"'and arrival_date<='"+to_date+"'");
				}
				List<List> rows = new ArrayList<>();
				while(rs.next())
				{
					List<String> cols = new ArrayList<>();
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
				session.setAttribute("tranport_report_head",rows);
				session.setAttribute("flag",flag);
				session.setAttribute("from",from_date);
				session.setAttribute("to",to_date);
				rd=req.getRequestDispatcher("T_Report.jsp");
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