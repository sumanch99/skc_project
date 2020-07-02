package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;

@WebServlet("/consignermasterupdate")
public class ConsignerMasterUpdate extends HttpServlet
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
			PreparedStatement ps = null;
			ServletContext ctx = getServletContext();
			try{
			Class.forName(ctx.getInitParameter("driver")).newInstance();
	        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
	        st=con.createStatement();
	        String c_id=req.getParameter("c_id");
	        String c_name=req.getParameter("c_name");
	        String c_code=req.getParameter("c_code");
	        String c_address=req.getParameter("c_address");
	        String c_no=req.getParameter("c_no");
	        String c_gno=req.getParameter("c_gno");
	        //res.getWriter().print(c_id);
	        String sql="UPDATE consigner_master SET consigner_name = ?,consigner_code = ?,address = ?,contact_no =?,gst_no =? WHERE consigner_id = '"+c_id+"'";
			ps = con.prepareStatement(sql);
			ps.setString(1, c_name);
            ps.setString(2, c_code);
            ps.setString(3, c_address);
            ps.setString(4, c_no);
            ps.setString(5, c_gno);
            int i =(int)ps.executeUpdate();
	      	if(i>0)
            {
            	RequestDispatcher rd=req.getRequestDispatcher("UpdateConsignerSuccess.jsp");
				rd.forward(req,res);
            }
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