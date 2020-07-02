package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;

@WebServlet("/inwardlrdetailsupdate")
public class InwardLRDetailsUpdate extends HttpServlet
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
	        String inward_lr_details_id = req.getParameter("inward_lr_details_id");
	        String balno = req.getParameter("balno");
	        String baltype = req.getParameter("baltype");
	        String no_of_pieces = req.getParameter("no_of_pieces");
	        String quality = req.getParameter("quality");
	        String rate = req.getParameter("rate");

	        rs=st.executeQuery("SELECT quality_id FROM quality_master WHERE quality='"+quality+"'");
	        rs.next();
	        String q_id = rs.getString(1);
	        st=con.createStatement();
	        int i=st.executeUpdate("UPDATE inward_lr_details SET bal_cartoon_no='"+balno+"',bal_cartoon_type='"+baltype+"',no_of_pieces='"+no_of_pieces+"',quality_id='"+q_id+"',rate='"+rate+"' WHERE inward_lr_details_id='"+inward_lr_details_id+"'");
            RequestDispatcher rd=req.getRequestDispatcher("UpdateInwardLRDetailsSuccess.jsp");
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