package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;

@WebServlet("/updateinward")
public class UpdateInward extends HttpServlet
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
			Statement st2 = null;
			ResultSet rs2 = null;
			PreparedStatement ps = null;
			ServletContext ctx = getServletContext();
			try{
			Class.forName(ctx.getInitParameter("driver")).newInstance();
	        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
	        st=con.createStatement();
	        String inward_id=req.getParameter("inward_id");
	        //res.getWriter().print(c_id);
	        rs=st.executeQuery("SELECT * FROM inward_head WHERE inward_id='"+inward_id+"'");
	        List<String> inward = new ArrayList<>();
	        if(rs.next())
	        {
	        	inward_id=rs.getString(1);
		        String transport_name=rs.getString(2);
		        String from_station=rs.getString(3);
		        String dispatch_date=rs.getString(4);
		        String challan_no=rs.getString(5);
		        String challan_date=rs.getString(6);
		        String lorry_no=rs.getString(7);
		        String arrival_date=rs.getString(8);
		        String total_bales=rs.getString(9);
		        inward.add(inward_id);
		        inward.add(transport_name);
		        inward.add(from_station);
		        inward.add(dispatch_date);
		        inward.add(challan_no);
		        inward.add(challan_date);
		        inward.add(lorry_no);
		        inward.add(arrival_date);
		        inward.add(total_bales);	
	        }
	        session.setAttribute("inward",inward);
	        st=con.createStatement();
	        rs=st.executeQuery("SELECT * FROM inward_lr_details WHERE inward_details_id = (SELECT inward_details_id FROM inward_details WHERE  inward_id = '"+inward_id+"' LIMIT 1)");
	        if(rs.next())
	        	session.setAttribute("loaded",1);
	        else
	        	session.setAttribute("loaded",0);
            RequestDispatcher rd=req.getRequestDispatcher("update_inward.jsp");
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