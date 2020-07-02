package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;
import java.time.*;

@WebServlet("/outward")
public class Outward extends HttpServlet
{
	public int flag=0;
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		
		Connection con = null;
		Statement st = null;
		Statement st1=null;
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
			ServletContext ctx = getServletContext();
			try{
				if(req.getParameter("quality")!=null)
				{
					String quality=req.getParameter("quality");
					session.setAttribute("selectedquality",quality);
					Class.forName(ctx.getInitParameter("driver")).newInstance();
			        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
			        st=con.createStatement();
			        rs=st.executeQuery("SELECT quality_id FROM quality_master WHERE quality='"+quality+"'");
			        rs.next();
			        String q_id=rs.getString(1);
			        st=con.createStatement();
			        rs=st.executeQuery("SELECT inward_lr_details_id,consigner_id,bill_no,bill_date,bal_cartoon_no,bal_cartoon_type,no_of_pieces,quality_id,rate FROM inward_details JOIN inward_lr_details ON(inward_details.inward_details_id=inward_lr_details.inward_details_id AND quality_id='"+q_id+"' AND inward_lr_details.outward_flag='f')");
			        List<List> outward_options = new ArrayList<>();
			        while(rs.next())
			        {
			        	List<String> cols=new ArrayList<>();
			        	cols.add(rs.getString(1));
			        	Statement st2=con.createStatement();
			        	ResultSet rs2=st2.executeQuery("SELECT consigner_code FROM consigner_master WHERE consigner_id='"+rs.getString(2)+"'");
			        	rs2.next();
			        	cols.add(rs2.getString(1));
			        	cols.add(rs.getString(3));
			        	cols.add(rs.getString(4));
			        	cols.add(rs.getString(5));
			        	cols.add(rs.getString(6));
			        	cols.add(rs.getString(7));
			        	cols.add(quality);
			        	cols.add(rs.getString(9));
			        	outward_options.add(cols);
			        }
			        session.setAttribute("outward_options",outward_options);
			        session.setAttribute("qualitygiven",1);
			        session.setAttribute("stocktransfer",0);
			        rd=req.getRequestDispatcher("outward.jsp");
			        rd.forward(req,res);
				}
				else if (req.getParameter("inward_lr_details_id")!=null) 
				{
					Class.forName(ctx.getInitParameter("driver")).newInstance();
			        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
					String inward_lr_details_id[]=req.getParameterValues("inward_lr_details_id");
					st=con.createStatement();
					rs=st.executeQuery("SELECT MAX(bal_id) FROM order_head");
					rs.next();
					String bal_id = rs.getString(1);
					for(String id:inward_lr_details_id)
					{
						st=con.createStatement();
						int s=st.executeUpdate("INSERT INTO outward_bales (bal_id,inward_lr_details_id) VALUES('"+bal_id+"','"+id+"')");
						st1=con.createStatement();
						int z = st1.executeUpdate("UPDATE inward_lr_details SET outward_flag='true' WHERE inward_lr_details_id='"+id+"'");
					}	
					req.removeAttribute("inward_lr_details_id");
					req.removeAttribute("quality");
					res.getWriter().print("Done");
					rd=req.getRequestDispatcher("d_cartsuccess.jsp");
					rd.forward(req,res);
 
				}
				else
				{

					Class.forName(ctx.getInitParameter("driver")).newInstance();
			        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
			        
			        LocalDate today = LocalDate.now( ZoneId.of( "Asia/Kolkata" ) ) ;
					try{
						st=con.createStatement();
						rs=st.executeQuery("SELECT * FROM order_head WHERE date='"+today.toString()+"'");
						if(!rs.next())
						{
							st=con.createStatement();
							int r=st.executeUpdate("INSERT INTO order_head(date) VALUES('"+today.toString()+"')");
						}
						
					}catch(SQLException e){}
					session.setAttribute("Date",today);
					st=con.createStatement();
					rs=st.executeQuery("SELECT MAX(order_no) FROM order_head");
					rs.next();
					String order_no = rs.getString(1);
					session.setAttribute("OrderNo",order_no);
			        st=con.createStatement();
			        rs=st.executeQuery("SELECT quality FROM quality_master");
			        Set<String> qualities = new HashSet<>();
			        while(rs.next())
			        {
			        	qualities.add(rs.getString(1));
			        }
			        session.setAttribute("qualities",qualities);
			        session.setAttribute("qualitygiven",0);
			        session.setAttribute("stocktransfer",0);

			        st=con.createStatement();
					rs=st.executeQuery("SELECT y.inward_lr_details_id,consigner_id,bal_cartoon_no,quality,hsn_code,cgst_rate,sgst_rate,no_of_pieces,rate,y.inward_details_id FROM (SELECT x.inward_lr_details_id,bal_cartoon_no,quality,hsn_code,cgst_rate,sgst_rate,no_of_pieces,rate,inward_details_id FROM ((SELECT inward_lr_details_id,bal_cartoon_no,quality,hsn_code,cgst_rate,sgst_rate,no_of_pieces,rate,inward_details_id FROM quality_master JOIN inward_lr_details ON (quality_master.quality_id=inward_lr_details.quality_id)) AS x JOIN outward_bales ON (x.inward_lr_details_id=outward_bales.inward_lr_details_id AND outward_bales.bal_id='"+order_no+"'))) AS y JOIN inward_details ON (y.inward_details_id=inward_details.inward_details_id);");
					
					List<List> deliveryCart = new ArrayList<>();
					while(rs.next())
					{
						List<String> cols = new ArrayList<>();
						cols.add(rs.getString(1));
						String c_id=rs.getString(2);
						Statement st3=con.createStatement();
						ResultSet rs3=st3.executeQuery("SELECT consigner_code FROM consigner_master WHERE consigner_id='"+c_id+"'");
						rs3.next();
						cols.add(rs3.getString(1));
						cols.add(rs.getString(3));
						cols.add(rs.getString(4));
						cols.add(rs.getString(5));
						cols.add(rs.getString(6));
						cols.add(rs.getString(7));
						cols.add(rs.getString(8));
						cols.add(rs.getString(9));
						cols.add(rs.getString(10));
						deliveryCart.add(cols);
					}
					session.setAttribute("deliveryCart",deliveryCart);

			        rd=req.getRequestDispatcher("outward.jsp");
			        rd.forward(req,res);
			    }
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