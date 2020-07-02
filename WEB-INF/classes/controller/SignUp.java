package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/signup")
public class SignUp extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		RequestDispatcher rd=req.getRequestDispatcher("signup.jsp");
		rd.forward(req,res);
	}
}