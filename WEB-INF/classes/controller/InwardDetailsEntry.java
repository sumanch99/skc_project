package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;

@WebServlet("/inwardlrdetailsentry")

public class InwardDetailsEntry extends HttpServlet
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

	        String inward_details_id = req.getParameter("inward_details_id");
	        String[] balno = req.getParameterValues("balno");
	        String[] baltype = req.getParameterValues("baltype");
	        String[] no_of_pieces = req.getParameterValues("no_of_pieces");
	        String[] quality = req.getParameterValues("quality");
	        String[] rate = req.getParameterValues("rate");
	        String flag="FALSE";
	        //res.getWriter().print("Before !");
	        
	        for(int i=0;i<balno.length;i++)
	        {
	        	//res.getWriter().print("Entry!");
	        	st=null;
	        	st=con.createStatement();
	        	rs=st.executeQuery("SELECT quality_id FROM quality_master WHERE quality='"+quality[i]+"'");
	        	rs.next();
	        	
	        	String quality_id = rs.getString(1);
	        	st=null;
	        	st=con.createStatement();
	        	res.getWriter().print("Got - 1!");
	        	int x=(int)st.executeUpdate("INSERT INTO inward_lr_details (inward_details_id,bal_cartoon_no,bal_cartoon_type,no_of_pieces,quality_id,rate,outward_flag) VALUES ('"+inward_details_id+"','"+balno[i]+"','"+baltype[i]+"','"+no_of_pieces[i]+"','"+quality_id+"','"+rate[i]+"','"+flag+"')");
	        	res.getWriter().print("Got 2 !");
	        }
	        //res.getWriter().print("Done!");
	        rd=req.getRequestDispatcher("gotoinwarddetails.jsp");
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
