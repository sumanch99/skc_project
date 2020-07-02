package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;

@WebServlet("/qualityMaster")

public class QualityMaster extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		HttpSession session = req.getSession();
		if(session.getAttribute("user_name")==null)
		{
			RequestDispatcher rd=req.getRequestDispatcher("userlogin");
			rd.forward(req,res);
		}
		else{
			ServletContext ctx = getServletContext();
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			ResultSet rs2=null;
			Statement st2=null;
			try{
			Class.forName(ctx.getInitParameter("driver")).newInstance();
	        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
	        st=con.createStatement();
	        rs = st.executeQuery("SELECT consigner_code FROM consigner_master");

	        ArrayList Rows = new ArrayList();

		    while (rs.next())
		    {
		       ArrayList row = new ArrayList();
		       row.add(rs.getString(1));
		       Rows.add(row);
		     }  
		       
	        req.getSession().setAttribute("c_codes", Rows);

	        rs = st.executeQuery("SELECT quality_id,quality,consigner_id,hsn_code,quality_description,cgst_rate,sgst_rate FROM quality_master ORDER BY quality");
	        List<List> rows = new ArrayList<List>();
	        while(rs.next())
	        {
	        	List cols=new ArrayList<String>();
	        	cols.add(rs.getString(1));
	        	cols.add(rs.getString(2));
	        	String c_id = rs.getString(3);
	        	st2=null;
	        	st2=con.createStatement();
	        	rs2=st2.executeQuery("SELECT consigner_code FROM consigner_master WHERE consigner_id='"+c_id+"'");
	        	rs2.next();
	        	cols.add(rs2.getString(1));
	        	cols.add(rs.getString(4));
	        	cols.add(rs.getString(5));
	        	cols.add(rs.getString(6));
	        	cols.add(rs.getString(7));
	        	rows.add(cols);
	        } 
	        req.getSession().setAttribute("qualities", rows);

	        Set <String> engaged_qualities = new HashSet<>();
	        st=con.createStatement();
	        rs=st.executeQuery("SELECT quality_id FROM inward_lr_details");
	        while(rs.next())
	        {
	        	engaged_qualities.add(rs.getString(1));
	        }
	        req.getSession().setAttribute("engaged_qualities", engaged_qualities);
	        RequestDispatcher rd=req.getRequestDispatcher("qualityMaster.jsp");
			rd.forward(req,res);

			}catch(ClassNotFoundException e)
		    {
		    	res.getWriter().print("ClassNotFoundException !");
		    }
		    catch(SQLException e)
		    {
		    	res.getWriter().print("SQLException !");
		    }
		    catch(InstantiationException e)
			{
				res.getWriter().print("InstantiationException !");
			}
			catch(IllegalAccessException e)
		    {
		    	res.getWriter().print("IllegalAccessException");
		    }  
		    finally{
		    	try{
		    		if(rs!=null)
		    			{
		    				rs.close();
		    				
		    			}
		    		if (st!=null) 
		    			{
		    				st.close();
		    				
		    			}
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