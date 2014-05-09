package com.example.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.model.User;
import com.example.service.UserService;
import com.example.service.UserServiceImpl;

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
		UserService service = new UserServiceImpl();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User tempUser = new User(username, password);
		User realUser = null;
		try {
			realUser = service.findUserByUsername(username);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(username.equals("admin") && tempUser.equals(realUser)){
			request.getSession(true);
			HttpSession hs = request.getSession();
			hs.setAttribute("type", "admin");
			hs.setAttribute("username", username);
			//RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdminHome");
			//rd.forward(request, response);
			response.sendRedirect("AdminHome");
		}
		else if(username.equals("cust") && tempUser.equals(realUser)){
			request.getSession(true);
			HttpSession hs = request.getSession();
			hs.setAttribute("type", "cust");
			hs.setAttribute("username", username);
			//RequestDispatcher rd = getServletContext().getRequestDispatcher("/CustHome");
			//rd.forward(request, response);
			response.sendRedirect("CustHome");
		}
		else{
			request.setAttribute("msg", "Access denied!");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login");
			rd.forward(request, response);
		}
	}

}
