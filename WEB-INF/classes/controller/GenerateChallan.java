package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;
import java.time.*;

@WebServlet("/generatechallan")
public class GenerateChallan extends HttpServlet
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
		try{
			ServletContext ctx = getServletContext();
			Class.forName(ctx.getInitParameter("driver")).newInstance();
			con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
			st=con.createStatement();
			String order_no=req.getParameter("order_no");
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM order_details WHERE order_no='"+order_no+"'");
			if(rs.next())
			{
				session.setAttribute("from_godown",rs.getString(3));
				session.setAttribute("to_shop",rs.getString(4));
				session.setAttribute("total_bales",rs.getString(5));
				session.setAttribute("mutia",rs.getString(6));
				session.setAttribute("vehicle_no",rs.getString(7));
			}
			else{
				st=con.createStatement();
				rs=st.executeQuery("SELECT COUNT(*) FROM (SELECT x.inward_lr_details_id,bal_cartoon_no,quality,hsn_code,cgst_rate,sgst_rate,no_of_pieces,rate,inward_details_id FROM ((SELECT inward_lr_details_id,bal_cartoon_no,quality,hsn_code,cgst_rate,sgst_rate,no_of_pieces,rate,inward_details_id FROM quality_master JOIN inward_lr_details ON (quality_master.quality_id=inward_lr_details.quality_id)) AS x JOIN outward_bales ON (x.inward_lr_details_id=outward_bales.inward_lr_details_id))) AS y JOIN inward_details ON (y.inward_details_id=inward_details.inward_details_id)");
				rs.next();
				session.setAttribute("total_bales",rs.getString(1));
			}
			//To store bal_no & bal_type,inward_lr_details_id pair
			st=con.createStatement();
			rs=st.executeQuery("SELECT SUM(total_bales) FROM order_details");
			String orderedbales = "";
			if(rs.next())
				orderedbales = rs.getString(1);
			session.setAttribute("orderedbales",orderedbales);
			Map<String,List> map = new  HashMap<>();
			st=con.createStatement();
			rs=st.executeQuery("SELECT bal_cartoon_no,bal_cartoon_type,inward_lr_details_id FROM inward_lr_details");
			while(rs.next())
				{
					List<String> val = new ArrayList<>();
					val.add(rs.getString(2));
					val.add(rs.getString(3));
					map.put(rs.getString(1),val);
				}
			session.setAttribute("balnobaltypepair",map);
			
			rd=req.getRequestDispatcher("OrderDelivery.jsp");
			rd.forward(req,res);
		}catch(ClassNotFoundException e)
	    {
	    	res.getWriter().print("ClassNotFoundException !");
	    }
	    catch(SQLException e)
	    {
	    	res.getWriter().print("SQLException !");
	    }
	    catch(IllegalAccessException e)
	    {
	    	res.getWriter().print("IllegalAccessException");
	    }
	    catch(InstantiationException e)
	    {
	    	res.getWriter().print("InstantiationException !");
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