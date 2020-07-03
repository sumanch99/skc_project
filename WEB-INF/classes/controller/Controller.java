package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/controller")
public class Controller extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		RequestDispatcher rd=null;
		HttpSession session = req.getSession();
		ServletContext ctx = getServletContext();
		int totalConsigners=0;
		int totalQualities=0;
		try{
			Class.forName(ctx.getInitParameter("driver")).newInstance();
	        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
	        st=con.createStatement();
	        rs=st.executeQuery("SELECT count(consigner_code) FROM consigner_master");
	        if(rs.next())
	        	totalConsigners=Integer.parseInt(rs.getString(1));
	        session.setAttribute("totalConsigners",totalConsigners);

	        st=con.createStatement();
	        rs=st.executeQuery("SELECT count(quality) FROM quality_master");
	        if(rs.next())
	        	totalQualities=Integer.parseInt(rs.getString(1));
	        session.setAttribute("totalQualities",totalQualities); 

			rd=req.getRequestDispatcher("index.jsp");
			rd.forward(req,res);
		}catch(ClassNotFoundException e)
		    {
		    	res.getWriter().print("ClassNotFoundException !");
		    }
		    catch(SQLException e)
		    {
		    	res.getWriter().print("SQLException !");
		    }
		    catch(Exception e)
		    {
		    	res.getWriter().print("Exception");
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