package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.*;
import java.sql.*;

@WebServlet("/consignerMaster")
public class ConsignerMaster extends HttpServlet
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

			try{	
			Class.forName(ctx.getInitParameter("driver")).newInstance();
	        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
	        st=con.createStatement();
	        rs = st.executeQuery("SELECT consigner_code,consigner_name,address,gst_no,consigner_id FROM consigner_master ORDER BY consigner_name");
	        
	        List<List> rows = new ArrayList<List>();
	        while(rs.next())
	        {
	        	List cols=new ArrayList<String>();
	        	cols.add(rs.getString(1));
	        	cols.add(rs.getString(2));
	        	cols.add(rs.getString(3));
	        	cols.add(rs.getString(4));
	        	cols.add(rs.getString(5));
	        	rows.add(cols);
	        } 

	        Set <String> engaged_consigners = new HashSet<>();
	        st=con.createStatement();
	        rs=st.executeQuery("SELECT consigner_id FROM quality_master");
	        while(rs.next())
	        {
	        	engaged_consigners.add(rs.getString(1));
	        }
	        st=con.createStatement();
	        rs=st.executeQuery("SELECT consigner_id FROM inward_details");
	        while(rs.next())
	        {
	        	engaged_consigners.add(rs.getString(1)); 
	        }

	        req.getSession().setAttribute("consigners", rows);
	        req.getSession().setAttribute("engaged_consigners", engaged_consigners);

	        RequestDispatcher rd=req.getRequestDispatcher("consignerMaster.jsp");
			rd.forward(req,res);

			}
			catch(ClassNotFoundException e)
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