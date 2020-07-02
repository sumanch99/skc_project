package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;

@WebServlet("/qualitymasterupdate")
public class QualityMasterUpdate extends HttpServlet
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
			
			ServletContext ctx = getServletContext();
			try{
			Class.forName(ctx.getInitParameter("driver")).newInstance();
	        con = DriverManager.getConnection(ctx.getInitParameter("connection"),ctx.getInitParameter("user"),ctx.getInitParameter("password"));
	        st=con.createStatement();
	        String q_id=req.getParameter("q_id");
	        String quality=req.getParameter("quality");
	        String c_code=req.getParameter("c_code");
	        String hsncode=req.getParameter("hsncode");
	        String q_desc=req.getParameter("q_desc");
	        String cgst=req.getParameter("cgst");
	        String sgst=req.getParameter("sgst");
	        //st=null;
	        st=con.createStatement();
	        //rs=null;
	        rs=st.executeQuery("SELECT consigner_id FROM consigner_master WHERE consigner_code='"+c_code+"'");
	        rs.next();
	        String c_id=rs.getString(1);

	        
	        String sql="UPDATE quality_master SET quality=?,consigner_id=?,hsn_code=?,quality_description=?,cgst_rate=?,sgst_rate=? WHERE quality_id = '"+q_id+"'";
			//ps=null;
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,quality);
            ps.setString(2,c_id);
            ps.setString(3,hsncode);
            ps.setString(4,q_desc);
            ps.setString(5,cgst);
            ps.setString(6,sgst);
            //res.getWriter().println(ps.toString());
            st=null;
            st=con.createStatement();
            int i = (int)st.executeUpdate(ps.toString());
            //ps.executeUpdate();
            /*res.getWriter().println(c_id);
            res.getWriter().println(hsncode);
            res.getWriter().println(q_desc);
            res.getWriter().println(cgst);
            res.getWriter().println(sgst);*/

            //
            
	      	if(i>0)
            {
            	RequestDispatcher rd=req.getRequestDispatcher("UpdateQualitySuccess.jsp");
				rd.forward(req,res);
            }
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