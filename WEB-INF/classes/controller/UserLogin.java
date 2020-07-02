package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/userlogin")
public class UserLogin extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		RequestDispatcher rd=req.getRequestDispatcher("userlogin.jsp");
		rd.forward(req,res);
	}
}