package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/consignermasterentry")
public class ConsignerMasterEntry extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		ServletContext ctx = getServletContext();
		HttpSession session = req.getSession();
		if(session.getAttribute("user_name")==null)
		{
			RequestDispatcher rd=req.getRequestDispatcher("userlogin");
			rd.forward(req,res);
		}
		String c_name=req.getParameter("c_name");
		String c_code=req.getParameter("c_code");
		String c_address=req.getParameter("c_address");
		String c_no=req.getParameter("c_no");
		String c_gno=req.getParameter("c_gno");

		/*res.getWriter().println(c_name);
		res.getWriter().println(c_code);
		res.getWriter().println(c_address);
		res.getWriter().println(c_no);
		res.getWriter().println(c_gno);*/

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		RequestDispatcher rd;
		int i=9;
		try{
			Class.forName(ctx.getInitParameter("driver")).newInstance();
	        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
	        st=con.createStatement();
	        
	        i=(int)st.executeUpdate("INSERT INTO consigner_master (consigner_name,consigner_code,address,contact_no,GST_no) VALUES ('"+c_name+"','"+c_code+"','"+c_address+"','"+c_no+"','"+c_gno+"')");
	        if(i>0)
	        {
	        	rd=req.getRequestDispatcher("EntrySuccessConsigner.jsp");
	        	rd.forward(req,res);
	        }
		}catch(ClassNotFoundException e)
	    {
	    	res.getWriter().print("ClassNotFoundException !");
	    }
	    catch(SQLException e)
	    {
	    	rd=req.getRequestDispatcher("EntryFailedConsigner.jsp");
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