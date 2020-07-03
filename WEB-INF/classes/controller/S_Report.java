package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.*;
import java.sql.*;

@WebServlet("/S_Report")
public class S_Report extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ServletContext ctx = getServletContext();
		RequestDispatcher rd=null;
		HttpSession session = req.getSession();
		int flag;
		if(session.getAttribute("user_name")==null)
		{
			rd=req.getRequestDispatcher("userlogin");
			rd.forward(req,res);
		}
		try{
			Class.forName(ctx.getInitParameter("driver")).newInstance();
	        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));		
			st=con.createStatement();
			rs=st.executeQuery("SELECT quality,hsn_code,quality_description,cgst_rate,sgst_rate,n FROM quality_master JOIN (SELECT DISTINCT quality_id,SUM(no_of_pieces) n FROM (SELECT quality_id,no_of_pieces FROM inward_lr_details WHERE outward_flag='f') AS x GROUP BY quality_id) AS Y ON (quality_master.quality_id=Y.quality_id)");
			List<List> rows = new ArrayList<>();
			while(rs.next())
			{
				List<String> cols = new ArrayList<>();
				cols.add(rs.getString(1));
				cols.add(rs.getString(2));
				cols.add(rs.getString(3));
				cols.add(rs.getString(4));
				cols.add(rs.getString(5));
				cols.add(rs.getString(6));
				rows.add(cols);
			}
			session.setAttribute("stock",rows);
			rd=req.getRequestDispatcher("S_Report.jsp");
			rd.forward(req,res);
		}
		catch(ClassNotFoundException e)
		{
		    res.getWriter().print("ClassNotFoundException !");
		}
		catch(SQLException e)
		{
		    res.getWriter().print("SQLException!");
		    rd.forward(req,res);
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