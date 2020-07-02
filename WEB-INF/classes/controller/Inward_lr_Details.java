package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;

@WebServlet("/inwardlrdetails")
public class Inward_lr_Details extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		RequestDispatcher rd=null;
		HttpSession session = req.getSession();
		if(session.getAttribute("user_name")==null)
		{
			rd=req.getRequestDispatcher("userlogin");
			rd.forward(req,res);
		}
		else
		{
			List<List> rows=null;
			List<String> cols=null;

			ServletContext ctx = getServletContext();
			try{
			Class.forName(ctx.getInitParameter("driver")).newInstance();
	        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
	        st=con.createStatement();

	        String inward_id=req.getParameter("inward_id");

	        String c_code=req.getParameter("c_code");
	        String c_bill_no=req.getParameter("c_bill_no");
	        String c_bill_date=req.getParameter("c_bill_date");
	        String c_lr_no=req.getParameter("c_lr_no");
	        int r_bales=Integer.parseInt(req.getParameter("remainingBales"));
	        List<String> r_d = new ArrayList<String>();
	        r_d.add(inward_id);
	        r_d.add(c_code);
	        r_d.add(c_bill_no);
	        r_d.add(c_bill_date);
	        r_d.add(c_lr_no);
	        req.getSession().setAttribute("recentdetails", r_d);
	        String lr_no=r_d.get(4);
			String[] a = lr_no.split("/"); 
			String x = a[a.length-1];
			int reqBales=Integer.parseInt(x);
			if(r_bales<reqBales)
			{
				rd=req.getRequestDispatcher("InwardDetailsEntryFailed.jsp");
				rd.forward(req,res);
			}
	        //res.getWriter().print("recentdetails added !");
	        //res.getWriter().print("Before Getting 0");
	        rs=st.executeQuery("SELECT consigner_id FROM consigner_master WHERE consigner_code='"+c_code+"'");
	        //res.getWriter().println("GOT IT! 0");
	        rs.next();
	        String c_id=rs.getString(1);

	        //res.getWriter().print(c_id);
	        st=null;
	        st=con.createStatement();
	        int i = (int)st.executeUpdate("INSERT INTO inward_details (inward_id,consigner_id,bill_no,bill_date,lr_no) VALUES('"+inward_id+"','"+c_id+"','"+c_bill_no+"','"+c_bill_date+"','"+c_lr_no+"')");
	        //res.getWriter().println("GOT IT!");
	        st=null;
	        st=con.createStatement();
	        //rs=null;
	        //res.getWriter().print("Before Getting 2");

	        rs=st.executeQuery("SELECT * FROM inward_details WHERE bill_no='"+c_bill_no+"' AND lr_no='"+c_lr_no+"'");
	        
	        //res.getWriter().println("THere");
	        rs.next();
	        String inward_details_id=rs.getString(1);
	        //res.getWriter().println("Here");
	        req.getSession().setAttribute("inwarddetails", inward_details_id);

	        st=null;
	        st=con.createStatement();
	        rs=st.executeQuery("SELECT quality FROM quality_master");
	        List<String> quality = new ArrayList<String>();
	        while(rs.next())
	        {
	        	quality.add(rs.getString(1));
	        }
	        req.getSession().setAttribute("quality", quality);

	        rs = st.executeQuery("SELECT * FROM inward_lr_details");

	        //res.getWriter().print("GOT IT! 3");
	        rows = new ArrayList<List>();
	        while(rs.next())
	        {
	        	cols=new ArrayList<String>();
	        	cols.add(rs.getString(1));
	        	cols.add(rs.getString(2));
	        	cols.add(rs.getString(3));
	        	cols.add(rs.getString(4));
	        	cols.add(rs.getString(5));
	        	cols.add(rs.getString(6));
	        	cols.add(rs.getString(7));
	        	cols.add(rs.getString(8));
	        	rows.add(cols);
	        } 
	        req.getSession().setAttribute("inwardlrdetails", rows);

			rd=req.getRequestDispatcher("inward_lr_details.jsp");
			rd.forward(req,res);
			}catch(ClassNotFoundException e)
	    {
	    	res.getWriter().print("ClassNotFoundException !");
	    }
	    catch(SQLException e)
	    {
	    	rd=req.getRequestDispatcher("InwardDetailsEntryFailed.jsp");
	        rd.forward(req,res);
	    }
	    /*catch(Exception e)
	    {
	    	res.getWriter().print("Exception");
	    }*/
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