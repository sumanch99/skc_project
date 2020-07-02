package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;

@WebServlet("/updateinwardlrdetails")
public class UpdateInwardLRDetails extends HttpServlet
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
            
	        rs=st.executeQuery("SELECT * FROM inward_lr_details WHERE inward_lr_details_id='"+inward_lr_details_id+"'");
	        List<String> inward_lr_details = new ArrayList<>();
	        if(rs.next())
	        {
	        	inward_lr_details.add(rs.getString(1));
	        	inward_lr_details.add(rs.getString(2));
	        	inward_lr_details.add(rs.getString(3));
	        	inward_lr_details.add(rs.getString(4));
	        	inward_lr_details.add(rs.getString(5));
	        	Statement st2=con.createStatement();
	        	ResultSet rs2=st2.executeQuery("SELECT quality FROM quality_master WHERE quality_id='"+rs.getString(6)+"'");
	        	rs2.next();
	        	inward_lr_details.add(rs2.getString(1));
	        	inward_lr_details.add(rs.getString(7));
	        	inward_lr_details.add(rs.getString(8));
	        	
	        }
	        session.setAttribute("inward_lr_details",inward_lr_details);
	        st=con.createStatement();
	        List<String> qualities = new  ArrayList<>();
	        rs=st.executeQuery("SELECT quality FROM quality_master");
	        while(rs.next())
	        {
	        	qualities.add(rs.getString(1));
	        }
	        session.setAttribute("qualities",qualities);
            RequestDispatcher rd=req.getRequestDispatcher("UpdateInwardLRDetails.jsp");
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