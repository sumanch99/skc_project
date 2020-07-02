package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/qualitymasterentry")
public class QualityMasterEntry extends HttpServlet
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
		String q_name=req.getParameter("q_name");
		String c_code=req.getParameter("c_code");
		String h_code=req.getParameter("h_code");
		String q_des=req.getParameter("q_des");
		String cgst=req.getParameter("cgst");
		String sgst=req.getParameter("sgst");
		String c_id="";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		RequestDispatcher rd;
		int i=9;
		try{
			Class.forName(ctx.getInitParameter("driver")).newInstance();
	        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
	        st=con.createStatement();
	        i=0;
	        rs=st.executeQuery("SELECT consigner_id FROM consigner_master WHERE consigner_code='"+c_code+"'");
	        if(rs.next())
	        {
	        	c_id =rs.getString(1);
	        }
	        i=(int)st.executeUpdate("INSERT INTO quality_master (quality,consigner_id,hsn_code,quality_description,cgst_rate,sgst_rate) VALUES ('"+q_name+"','"+c_id+"','"+h_code+"','"+q_des+"','"+cgst+"','"+sgst+"')");
	        if(i>0)
	        {
	        	rd=req.getRequestDispatcher("EntrySuccessQuality.jsp");
	        	rd.forward(req,res);
	        }
		}catch(ClassNotFoundException e)
	    {
	    	res.getWriter().print("ClassNotFoundException !");
	    }
	    catch(SQLException e)
	    {
	    	rd=req.getRequestDispatcher("EntryFailedQuality.jsp");
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