package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;

@WebServlet("/editinwarddetails")
public class InwardDetails extends HttpServlet
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
			ServletContext ctx = getServletContext();
			try{
			Class.forName(ctx.getInitParameter("driver")).newInstance();
	        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
	        st=con.createStatement();
	        String ch_no=req.getParameter("ch_no");
	        if(ch_no!=null)
	        	req.getSession().setAttribute("current_ch_no", ch_no);
	        if(ch_no==null)
	        	ch_no=req.getSession().getAttribute("current_ch_no").toString();
	        rs = st.executeQuery("SELECT * FROM inward_head WHERE challan_no='"+ch_no+"'");
	        List<List> rows = new ArrayList<List>();
	        int totalBales=0;
	        while(rs.next())
	        {
	        	List cols=new ArrayList<String>();
	        	cols.add(rs.getString(1));
	        	cols.add(rs.getString(2));
	        	cols.add(rs.getString(3));
	        	cols.add(rs.getString(4));
	        	cols.add(rs.getString(5));
	        	cols.add(rs.getString(6));
	        	cols.add(rs.getString(7));
	        	cols.add(rs.getString(8));
	        	cols.add(rs.getString(9));
	        	totalBales = Integer.parseInt(rs.getString(9));
	        	rows.add(cols);
	        } 
	        req.getSession().setAttribute("inwardheaddetails", rows);
	        st=null;
	        st=con.createStatement();
	        rs = st.executeQuery("SELECT consigner_code FROM consigner_master");
	        ArrayList Rows = new ArrayList();

		    while (rs.next())
		    {
		       ArrayList row = new ArrayList();
		       row.add(rs.getString(1));
		       Rows.add(row);
		     }  
		       
	        req.getSession().setAttribute("c_codes", Rows);

	        rs=null;
	        st=null;
	        st=con.createStatement();
	        rs=st.executeQuery("SELECT inward_id FROM inward_head WHERE challan_no='"+ch_no+"'");
	        rs.next();
	        String thisinward_id=rs.getString(1);

	        //res.getWriter().print("this!");
	        st=null;
	        st=con.createStatement();
	        rs=st.executeQuery("SELECT COUNT(inward_lr_details_id) FROM inward_lr_details WHERE inward_details_id IN (SELECT inward_details_id FROM inward_details WHERE inward_id='"+thisinward_id+"')");
	        int currentBales=0;
	        if(rs.next())
	        	currentBales = Integer.parseInt(rs.getString(1));
	        session.setAttribute("totalBales",totalBales);
	        session.setAttribute("currentBales",currentBales);
	        st=null;
	        st=con.createStatement();
	        rs=st.executeQuery("SELECT inward_details.inward_details_id,inward_lr_details_id,lr_no,consigner_id,bill_no,bill_date,bal_cartoon_no,bal_cartoon_type,no_of_pieces,quality_id,rate FROM inward_details JOIN inward_lr_details ON (inward_details.inward_details_id = inward_lr_details.inward_details_id AND inward_id='"+thisinward_id+"') ORDER BY lr_no");
	        //res.getWriter().print("that!");

	        rows=new ArrayList<List>();
	        while(rs.next())
	        {
	        	List cols=new ArrayList<String>();
	        	cols.add(rs.getString(3));
	        	Statement st2=con.createStatement();
				ResultSet rs2=st2.executeQuery("SELECT consigner_code FROM consigner_master WHERE consigner_id='"+rs.getString(4)+"'");
				rs2.next();
				String consigner_code=rs2.getString(1);
	        	cols.add(consigner_code);
	        	cols.add(rs.getString(5));
	        	cols.add(rs.getString(6));
	        	cols.add(rs.getString(7));
	        	cols.add(rs.getString(8));
	        	cols.add(rs.getString(9));
	        	st2=con.createStatement();
	        	rs2=st2.executeQuery("SELECT quality FROM quality_master WHERE quality_id='"+rs.getString(10)+"'");
				rs2.next();
				String quality=rs2.getString(1);
				cols.add(quality);
				cols.add(rs.getString(11));
				cols.add(rs.getString(2));
				cols.add(rs.getString(1));
	        	rows.add(cols);
	        }
	      
			req.getSession().setAttribute("alldetlrjoin", rows);	
			List<String> delivered = new ArrayList<>();
			st=con.createStatement();
			rs = st.executeQuery("SELECT inward_lr_details_id FROM inward_lr_details WHERE outward_flag='t'");
			while(rs.next())
			{
				delivered.add(rs.getString(1));
			}
			session.setAttribute("delivered",delivered);
			rd=req.getRequestDispatcher("inward_details.jsp");
			rd.forward(req,res);
		}catch(ClassNotFoundException e)
	    {
	    	res.getWriter().print("ClassNotFoundException !");
	    }
	    catch(SQLException e)
	    {
	    	res.getWriter().print("SQLException!");
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