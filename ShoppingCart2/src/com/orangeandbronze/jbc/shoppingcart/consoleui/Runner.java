package com.orangeandbronze.jbc.shoppingcart.consoleui;

import com.orangeandbronze.jbc.shoppingcart.dao.ShoppingDaoException;
import com.orangeandbronze.jbc.shoppingcart.service.ShoppingService;
import com.orangeandbronze.jbc.shoppingcart.service.ShoppingServiceImpl;


public class Runner {
	
	public static void main(String[] args) {
		ShoppingService shoppingService = new ShoppingServiceImpl();
		try {
			System.out.println(shoppingService.getCartGrandTotal());
		} catch (ShoppingDaoException e) {
			e.getMessage();
		}
	}
}
