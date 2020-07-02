package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/about")
public class About extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		RequestDispatcher rd=req.getRequestDispatcher("about.jsp");
		rd.forward(req,res);
	}
}