package com.example.dao.cart;

//import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import com.example.dao.DaoException;

public class CartDaoImplTest {

	//@Test
	public void testAddToCart() throws SQLException, DaoException {
		new CartDaoImpl().addToCart("cus1", "Crop", 3);
	}
	
	//@Test
	public void testCheckOutCart() throws SQLException, DaoException {
		new CartDaoImpl().checkOutCart("cus1");
	}
	
	//@Test
	public void testClearCart() throws SQLException, DaoException {
		new CartDaoImpl().clearCart("cus1");
	}
	
	//@Test
	public void testRemoveFromCart() throws SQLException, DaoException {
		new CartDaoImpl().removeFromCart("cus1", "Crop", 1);
	}
	
	@Test
	public void updateAllCart() throws DaoException, SQLException {
		new CartDaoImpl().updateAllCart("Crop", 60);;
	}

}