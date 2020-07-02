package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;
import java.time.*;

@WebServlet("/deliver")
public class Deliver extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		RequestDispatcher rd=null;
		HttpSession session = req.getSession();
		String order_no = session.getAttribute("OrderNo").toString();
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
			String f_godown_name = req.getParameter("f_godown_name");
			String f_godown_address = req.getParameter("f_godown_address");
			String t_shop_name = req.getParameter("t_shop_name");
			String t_shop_address = req.getParameter("t_shop_address");
			String balesQuantity = req.getParameter("balesQuantity");
			String mutia = req.getParameter("mutia");
			String v_no = req.getParameter("v_no");
			//res.getWriter().print(order_no);
			int i=st.executeUpdate("INSERT INTO order_details (order_no,from_godown,to_shop,total_bales,mutia,vehicle_no) VALUES('"+order_no+"','"+(f_godown_name+"//"+f_godown_address)+"','"+(t_shop_name+"//"+t_shop_address)+"','"+balesQuantity+"','"+mutia+"','"+v_no+"')");
			st=con.createStatement();
			rs=st.executeQuery("select MAX(order_detils_id) FROM order_details");
			String m_id ="";
			if(rs.next())
			{
				m_id = rs.getString(1);
				st=con.createStatement();
				rs=st.executeQuery("SELECT * FROM order_details WHERE order_no='"+order_no+"' AND order_detils_id='"+m_id+"'");
			}
			else
			{
				st=con.createStatement();
				rs=st.executeQuery("SELECT * FROM order_details WHERE order_no='"+order_no+"'");
			}
			List<String> deliverdetails = new ArrayList<>();
			if(rs.next())
			{
				deliverdetails.add(rs.getString(1));
				deliverdetails.add(rs.getString(2));
				deliverdetails.add(rs.getString(3));
				deliverdetails.add(rs.getString(4));
				deliverdetails.add(rs.getString(5));
				deliverdetails.add(rs.getString(6));
				deliverdetails.add(rs.getString(7));
			}
			session.setAttribute("deliverdetails",deliverdetails);

			rd=req.getRequestDispatcher("DeliverChallan.jsp");
			rd.forward(req,res);
		}catch(ClassNotFoundException e)
	    {
	    	res.getWriter().print("ClassNotFoundException !");
	    }
	    catch(SQLException e)
	    {
	    	try{
	    	st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM order_details WHERE order_no='"+order_no+"'");
			List<String> deliverdetails = new ArrayList<>();
			if(rs.next())
			{
				deliverdetails.add(rs.getString(1));
				deliverdetails.add(rs.getString(2));
				deliverdetails.add(rs.getString(3));
				deliverdetails.add(rs.getString(4));
				deliverdetails.add(rs.getString(5));
				deliverdetails.add(rs.getString(6));
				deliverdetails.add(rs.getString(7));
			}
			session.setAttribute("deliverdetails",deliverdetails);
			rd=req.getRequestDispatcher("DeliverChallan.jsp");
			rd.forward(req,res);
			}catch(SQLException e2)
			{
				res.getWriter().print("SQLException 2");
			}
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