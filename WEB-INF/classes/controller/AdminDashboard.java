package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.*;
import java.sql.*;
import java.util.*;
import java.security.*;

@WebServlet("/admindashboard")
public class AdminDashboard extends HttpServlet
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
		if(session.getAttribute("admin_user_name")!=null)
		{
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			ServletContext ctx = getServletContext();
			try{
			Class.forName(ctx.getInitParameter("driver")).newInstance();
	        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
	        st=con.createStatement();
			rs=st.executeQuery("SELECT user_name,name,email,contact_number FROM pending_user");
	        List<List> rows = new ArrayList<List>();
		    while(rs.next())
		        {
		        	List cols=new ArrayList<String>();
		        	cols.add(rs.getString(1));
		        	cols.add(rs.getString(2));
		        	cols.add(rs.getString(3));
		        	cols.add(rs.getString(4));
		        	rows.add(cols);
		        } 
		    req.getSession().setAttribute("pendingusers", rows);

			RequestDispatcher rd=req.getRequestDispatcher("admindashboard.jsp");
			rd.forward(req,res);
			}
			catch(SQLException e)
		    {
		    	res.getWriter().print("SQLException!");
		    }
		    catch(ClassNotFoundException e)
		    {
		    	res.getWriter().print("ClassNotFoundException!");
		    }
		    catch(InstantiationException e)
		    {
		    	res.getWriter().print("InstantiationException!");
		    }
		    catch(IllegalAccessException e)
		    {
		    	res.getWriter().print("IllegalAccessException!");
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
		ServletContext ctx = getServletContext();

		String uname=req.getParameter("username");
		String pwd=req.getParameter("password");

		try{
			pwd = hashPassword(pwd);
		}catch(NoSuchAlgorithmException e)
		{
			res.getWriter().print("NoSuchAlgorithmException!");
		}

		if(uname==null)
		{
			RequestDispatcher rd=req.getRequestDispatcher("adminlogin");
			rd.forward(req,res);
		}

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		

		try{
			Class.forName(ctx.getInitParameter("driver")).newInstance();
	        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
	        st=con.createStatement();
	        rs=st.executeQuery("SELECT * FROM super_user where user_name ='"+uname+"' AND password ='"+pwd+"'" );
	        if(rs.next())
	        {
	        	session.setAttribute("admin_user_name",rs.getString("user_name"));
	        	rs=null;
	        	rs=st.executeQuery("SELECT user_name,name,email,contact_number FROM pending_user");
	        	List<List> rows = new ArrayList<List>();
		        while(rs.next())
		        {
		        	List cols=new ArrayList<String>();
		        	cols.add(rs.getString(1));
		        	cols.add(rs.getString(2));
		        	cols.add(rs.getString(3));
		        	cols.add(rs.getString(4));
		        	rows.add(cols);
		        } 
		        req.getSession().setAttribute("pendingusers", rows);
	        	RequestDispatcher rd=req.getRequestDispatcher("admindashboard.jsp");
				rd.forward(req,res);
				
	        }
	        else{
	        	RequestDispatcher rd=req.getRequestDispatcher("invalidadmin.jsp");
				rd.forward(req,res);
				
	        }
	    
		}
		catch(SQLException e)
	    {
	    	res.getWriter().print("SQLException!");
	    }
	    catch(ClassNotFoundException e)
	    {
	    	res.getWriter().print("ClassNotFoundException!");
	    }
	    catch(InstantiationException e)
	    {
	    	res.getWriter().print("InstantiationException!");
	    }
	    catch(IllegalAccessException e)
	    {
	    	res.getWriter().print("IllegalAccessException!");
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