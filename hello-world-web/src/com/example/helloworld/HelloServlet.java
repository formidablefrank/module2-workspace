package com.example.helloworld;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ping from HelloServlet.doGet()");
		String elsaMessage = "You can't marry a man you just met. " + new Date();
		request.setAttribute("frozenQuote", elsaMessage);
		//request, session, webcontext -> cargo carrier
		//used to forward request
		RequestDispatcher requestDispatcher = 
				getServletContext().getRequestDispatcher("/wherever-youre-going.jsp");
		requestDispatcher.forward(request, response);
		//servlet context is the tomcat container
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ping from doPost()");
	}

}
