package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;

@WebServlet("/UpdateQuality")
public class UpdateQuality extends HttpServlet
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
	        String q_id=req.getParameter("q_id");
	        //res.getWriter().print(c_id);
	        rs=st.executeQuery("SELECT * FROM quality_master WHERE quality_id='"+q_id+"'");
	        rs.next();
	        
	        String q_quality=rs.getString(2);
	        String q_cid=rs.getString(3);
	        String q_hasn=rs.getString(4);
	        String q_des=rs.getString(5);
	        String q_cgst=rs.getString(6);
	        String q_sgst=rs.getString(7);
	        st2=null;
	        st2=con.createStatement();
	        rs2=st2.executeQuery("SELECT consigner_code FROM consigner_master WHERE consigner_id='"+q_cid+"'");
	        rs2.next();
	        String q_ccode=rs2.getString(1);
			List<String> quality = new ArrayList<String>();
			quality.add(q_id);
			quality.add(q_quality);
			quality.add(q_ccode);
			quality.add(q_hasn);
			quality.add(q_des);
			quality.add(q_cgst);
			quality.add(q_sgst);
			

			session.setAttribute("quality", quality);
			List <String> consigners = new ArrayList<String>();
			st=null;
			st=con.createStatement();
			rs=st.executeQuery("SELECT consigner_code FROM consigner_master");
			while(rs.next())
				consigners.add(rs.getString(1));
			session.setAttribute("consigners", consigners);
            RequestDispatcher rd=req.getRequestDispatcher("update_quality.jsp");
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