package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;

@WebServlet("/userProfile")
public class UserProfile extends HttpServlet
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
			
			String username=session.getAttribute("user_name").toString();
			rs=st.executeQuery("SELECT * FROM login_user WHERE user_name='"+username+"'");
			List<String> user =null;
			if(rs.next())
			{
				user = new ArrayList<String>();
				user.add(rs.getString("user_name"));
				user.add(rs.getString("name"));
				user.add(rs.getString("email"));
				//user.add(rs.getString("password"));
				user.add(rs.getString("contact_number"));
			}
			session.setAttribute("user",user);

			/*List<String> userProfile = (ArrayList) session.getAttribute("user");
			res.getWriter().println("Username: "+userProfile.get(0));
			res.getWriter().println("name: "+userProfile.get(1));
			res.getWriter().println("email: "+userProfile.get(2));
			res.getWriter().println("contact_number: "+userProfile.get(3));
			*/
			RequestDispatcher rd=req.getRequestDispatcher("userprofile.jsp");
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