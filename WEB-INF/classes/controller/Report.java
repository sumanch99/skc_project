package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/report")
public class Report extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		
		RequestDispatcher rd=req.getRequestDispatcher("report.jsp");
		rd.forward(req,res);
	}
}