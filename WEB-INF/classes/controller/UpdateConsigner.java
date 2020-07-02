package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;

@WebServlet("/updateconsigner")
public class UpdateConsigner extends HttpServlet
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
	        //res.getWriter().print(c_id);
	        rs=st.executeQuery("SELECT * FROM consigner_master WHERE consigner_id='"+c_id+"'");
	        rs.next();
	        String c_name=rs.getString(2);
	        String c_code=rs.getString(3);
	        String c_address=rs.getString(4);
	        String c_number=rs.getString(5);
	        String c_gstno=rs.getString(6);

			List<String> consigner = new ArrayList<String>();
			consigner.add(c_id);
			consigner.add(c_name);
			consigner.add(c_code);
			consigner.add(c_address);
			consigner.add(c_number);
			consigner.add(c_gstno);
			session.setAttribute("consigner", consigner);
            RequestDispatcher rd=req.getRequestDispatcher("update_consigner.jsp");
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