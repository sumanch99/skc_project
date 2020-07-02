package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;

@WebServlet("/updateinwarddetails")
public class UpdateInwardDetails extends HttpServlet
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
	        String inward_details_id = req.getParameter("inward_details_id");
            
	        rs=st.executeQuery("SELECT * FROM inward_details WHERE inward_details_id='"+inward_details_id+"'");
	        List<String> inwarddetails = new ArrayList<>();
	        if(rs.next())
	        {
	        	inwarddetails.add(rs.getString(1));
	        	inwarddetails.add(rs.getString(2));
	        	Statement st2=con.createStatement();
	        	ResultSet rs2=st2.executeQuery("SELECT consigner_code FROM consigner_master WHERE consigner_id='"+rs.getString(3)+"'");
	        	rs2.next();
	        	inwarddetails.add(rs2.getString(1));
	        	inwarddetails.add(rs.getString(4));
	        	inwarddetails.add(rs.getString(5));
	        	inwarddetails.add(rs.getString(6));
	        }
	        session.setAttribute("inwarddetails",inwarddetails);
            RequestDispatcher rd=req.getRequestDispatcher("UpdateInwardDetails.jsp");
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