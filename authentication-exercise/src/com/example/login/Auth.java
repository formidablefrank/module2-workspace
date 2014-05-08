package com.example.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Auth
 */
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auth() {
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
		if(request.getParameter("username").equals(request.getParameter("password"))){
			request.setAttribute("username", request.getParameter("username"));
			//RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Home.jsp");
			//rd.forward(request, response);
			response.sendRedirect("Home.jsp");
		}
		else{
			request.setAttribute("msg", "Access denied!");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login");
			rd.forward(request, response);
		}
	}

}
