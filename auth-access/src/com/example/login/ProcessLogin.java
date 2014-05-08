package com.example.login;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProcessLogin
 */
public class ProcessLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("username", request.getParameter("username"));
		request.setAttribute("date", new Date());
		if(request.getParameter("username").equals("admin") && request.getParameter("password").equals("admin")){
			request.getSession(true);
			HttpSession hs = request.getSession();
			hs.setAttribute("type", "admin");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdminHome");
			rd.forward(request, response);
		}
		else if(request.getParameter("username").equals("cust") && request.getParameter("password").equals("cust")){
			request.getSession(true);
			HttpSession hs = request.getSession();
			hs.setAttribute("type", "cust");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/CustHome");
			rd.forward(request, response);
		}
		else{
			request.setAttribute("msg", "Access denied!");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login");
			rd.forward(request, response);
		}
	}

}
