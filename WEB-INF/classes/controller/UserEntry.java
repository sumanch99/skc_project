package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.security.*;

@WebServlet("/userentry")
public class UserEntry extends HttpServlet
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

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		ServletContext ctx = getServletContext();
		HttpSession session = req.getSession();
		String name=req.getParameter("name");
		String username=req.getParameter("username");
		String email=req.getParameter("email");
		String number=req.getParameter("number");
		String password=req.getParameter("password");

		try{
			password = hashPassword(password);
		}catch(NoSuchAlgorithmException e)
		{
			res.getWriter().print("Fatal Error Occured!");
		}

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		RequestDispatcher rd;
		int i=9;
		try{
			Class.forName(ctx.getInitParameter("driver")).newInstance();
	        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
	        st=con.createStatement();
	        rs=st.executeQuery("SELECT * from login_user WHERE user_name='"+username+"'");
	        if(rs.next())
	        {
	        	rd=req.getRequestDispatcher("EntryFailedUser.jsp");
	        	rd.forward(req,res);
	        }
	        
	        else{ 
	        	rs=st.executeQuery("SELECT * from login_user WHERE password='"+password+"'");
		        if(rs.next())
		        {
		        	rd=req.getRequestDispatcher("EntryFailedUser.jsp");
		        	rd.forward(req,res);
		        }
		        else
		        {
		        	i=(int)st.executeUpdate("INSERT INTO pending_user (user_name,name,email,password,contact_number) VALUES ('"+username+"','"+name+"','"+email+"','"+password+"','"+number+"')");
			        if(i>0)
			        {
			        	rd=req.getRequestDispatcher("EntrySuccessUser.jsp");
			        	rd.forward(req,res);
			        }
		    	}
		    }
		}catch(ClassNotFoundException e)
	    {
	    	res.getWriter().print("ClassNotFoundException !");
	    }
	    catch(SQLException e)
	    {
	    	rd=req.getRequestDispatcher("EntryFailedUser.jsp");
	        rd.forward(req,res);
	    }
	    catch(Exception e)
	    {
	    	res.getWriter().print("i:"+i);
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
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		doPost(req,res);
	}
}