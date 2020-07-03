package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/index")
public class Index extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		RequestDispatcher rd=req.getRequestDispatcher("controller");
		rd.forward(req,res);
	}
}