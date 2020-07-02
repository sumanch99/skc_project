package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.*;
import java.sql.*;
import java.util.*;
import java.security.*;

@WebServlet("/dashboard")
public class Dashboard extends HttpServlet
{
	//Hashing Password using SHA Secured Hashing Algorithm 
	public static String hashPassword(String password)throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("SHA");
		md.update(password.getBytes());
		byte[] b=md.digest();
		StringBuffer sb=new StringBuffer();
		for(byte b1:b)
			sb.append(Integer.toHexString(b1 & 0xff).toString());
		return sb.toString();
	}

	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		HttpSession session = req.getSession();
		if(session.getAttribute("user_name")!=null)
		{
			RequestDispatcher rd=req.getRequestDispatcher("dashboard.jsp");
			rd.forward(req,res);
		}

		ServletContext ctx = getServletContext();

		String uname=req.getParameter("username");
		String pwd=req.getParameter("password");

		try{
			pwd = hashPassword(pwd);
		}catch(NoSuchAlgorithmException e)
		{
			res.getWriter().print("Fatal Error Occured!");
		}

		if(uname==null)
		{
			RequestDispatcher rd=req.getRequestDispatcher("userlogin");
			rd.forward(req,res);
		}

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		

		try{
			Class.forName(ctx.getInitParameter("driver")).newInstance();
	        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
	        st=con.createStatement();
	        rs=st.executeQuery("SELECT * FROM login_user where user_name ='"+uname+"' AND password ='"+pwd+"'" );
	        if(rs.next())
	        {
	        	session.setAttribute("user_name",rs.getString("user_name"));
	        	RequestDispatcher rd=req.getRequestDispatcher("dashboard.jsp");
				rd.forward(req,res);
	        }
	        else{
	        	session = req.getSession();
	        	session.setAttribute("invalid_action","userlogin.jsp");
	        	RequestDispatcher rd=req.getRequestDispatcher("invalid.jsp");
				rd.forward(req,res);
	        }
	    

		    if(session.getAttribute("user_name")!=null)
			{
				RequestDispatcher rd=req.getRequestDispatcher("userlogin");
				rd.forward(req,res);
			}
		}
	    catch(Exception e)
	    {
	    	res.getWriter().print("Bad Request Gateway!");
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