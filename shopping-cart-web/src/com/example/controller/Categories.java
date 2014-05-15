package com.example.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.DaoException;
import com.example.dao.inventory.InventoryDao;
import com.example.dao.inventory.InventoryDaoImpl;
import com.example.model.Category;
import com.example.model.Inventory;
import com.example.service.AdminService;
import com.example.service.AdminServiceImpl;

/**
 * Servlet implementation class Categories
 */
public class Categories extends HttpServlet {
	private AdminService as;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Categories() {
        super();
        as = new AdminServiceImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Inventory inv = null;
		try {
			inv = as.viewProducts();
		} catch (DaoException e) {
			request.setAttribute("errorMsg", "Error in database connection. Try again later.");
			e.printStackTrace();
		} catch (SQLException e) {
			request.setAttribute("errorMsg", "Error in database query. Try again later.");
			e.printStackTrace();
		}
		if(inv != null){
			List<String> categoryList = new ArrayList<String>();
			for(Category cat: inv.getCategories()){
				categoryList.add(cat.getName());
			}
			request.setAttribute("categoryList", categoryList);
		}
		request.getServletContext().getRequestDispatcher("/WEB-INF/categories.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
