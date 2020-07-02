package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/gallery")
public class Gallery extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		RequestDispatcher rd=req.getRequestDispatcher("gallery.jsp");
		rd.forward(req,res);
	}
}