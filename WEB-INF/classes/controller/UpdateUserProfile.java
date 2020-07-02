package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;

@WebServlet("/updateuserprofile")
public class UpdateUserProfile extends HttpServlet
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
	        //st=con.createStatement();
	        String username=req.getParameter("username");
			String name=req.getParameter("name");
			String email=req.getParameter("email");
			String number=req.getParameter("number");

			String sql="UPDATE login_user SET name = ?,email = ?,contact_number = ? WHERE user_name = '"+username+"'";
			ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, number);
            int i =(int)ps.executeUpdate();
            if(i>0)
            {
            	RequestDispatcher rd=req.getRequestDispatcher("UpdateProfileSuccess.jsp");
				rd.forward(req,res);
            }
            else{
            	RequestDispatcher rd=req.getRequestDispatcher("UpdateProfileFailed.jsp");
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