package com.example.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.DaoException;
import com.example.service.CustomerService;
import com.example.service.CustomerServiceImpl;

/**
 * Servlet implementation class ClearCart
 */
public class ClearCart extends HttpServlet {
	private CustomerService cs;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClearCart() {
        super();
        cs = new CustomerServiceImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			cs.clearCart((String)request.getSession().getAttribute("username"));
			request.setAttribute("msgSuccess", "Success!");
		} catch (SQLException e) {
			request.setAttribute("errorMsg", "Error in database connection. Try again later.");
			e.printStackTrace();
		} catch (DaoException e) {
			request.setAttribute("errorMsg", "Error in database query. Try again later.");
			e.printStackTrace();
		}
		request.getServletContext().getRequestDispatcher("/viewCart").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
