package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;
@WebServlet("/inwardheadentry")
public class InwardHeadEntry extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		ServletContext ctx = getServletContext();
		HttpSession session = req.getSession();
		if(session.getAttribute("user_name")==null)
		{
			RequestDispatcher rd=req.getRequestDispatcher("userlogin");
			rd.forward(req,res);
		}
		String user = session.getAttribute("user_name").toString();
		String ts_name=req.getParameter("ts_name");
		String fr_station=req.getParameter("fr_station");
		String ds_date=req.getParameter("ds_date");
		String ch_no=req.getParameter("ch_no");
		String ch_date=req.getParameter("ch_date");
		String lorry_no=req.getParameter("lorry_no");
		String av_date=req.getParameter("av_date");
		String total_bales=req.getParameter("total_bales");
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		RequestDispatcher rd;
		int i=9;
		try{
			Class.forName(ctx.getInitParameter("driver")).newInstance();
	        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
	        st=con.createStatement();
	        i=(int)st.executeUpdate("INSERT INTO inward_head (transport_name,from_station,dispatch_date,challan_no,challan_date,lorry_no,arrival_date,total_bales,user_name) VALUES ('"+ts_name+"','"+fr_station+"','"+ds_date+"','"+ch_no+"','"+ch_date+"','"+lorry_no+"','"+av_date+"','"+total_bales+"','"+user+"')");
	        if(i>0)
	        {
	        	rd=req.getRequestDispatcher("EntrySuccess.jsp");
	        	rd.forward(req,res);
	        }

	       
		}catch(ClassNotFoundException e)
	    {
	    	res.getWriter().print("ClassNotFoundException !");
	    }
	    catch(SQLException e)
	    {
	    	rd=req.getRequestDispatcher("EntryFailed.jsp");
	        rd.forward(req,res);
	    }
	    catch(Exception e)
	    {
	    	res.getWriter().print("i:"+i);
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
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		doPost(req,res);
	}
}