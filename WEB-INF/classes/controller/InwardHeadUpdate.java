package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;

@WebServlet("/inwardheadupdate")
public class InwardHeadUpdate extends HttpServlet
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
	        String inward_id = req.getParameter("inward_id"); 
	        String ts_name = req.getParameter("ts_name");
	        String fr_station = req.getParameter("fr_station");
	        String ds_date = req.getParameter("ds_date");
	        String ch_no = req.getParameter("ch_no");
	        String ch_date = req.getParameter("ch_date");
	        String lorry_no = req.getParameter("lorry_no");
	        String av_date = req.getParameter("av_date");
	        String total_bales = req.getParameter("total_bales");
	        if(total_bales==null)
	        {
	        	int  i = st.executeUpdate("UPDATE inward_head SET transport_name='"+ts_name+"',from_station='"+fr_station+"',dispatch_date='"+ds_date+"',challan_no='"+ch_no+"',challan_date='"+ch_date+"',lorry_no='"+lorry_no+"',arrival_date='"+av_date+"' WHERE inward_id='"+inward_id+"'");
	        }
	        else{
	        	int  i = st.executeUpdate("UPDATE inward_head SET transport_name='"+ts_name+"',from_station='"+fr_station+"',dispatch_date='"+ds_date+"',challan_no='"+ch_no+"',challan_date='"+ch_date+"',lorry_no='"+lorry_no+"',arrival_date='"+av_date+"',total_bales='"+total_bales+"' WHERE inward_id='"+inward_id+"'");
	        }
	        RequestDispatcher rd=req.getRequestDispatcher("UpdateInwardSuccess.jsp");
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