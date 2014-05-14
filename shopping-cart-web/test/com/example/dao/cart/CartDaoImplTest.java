package com.example.dao.cart;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import com.example.dao.DaoException;

public class CartDaoImplTest {

	@Test
	public void testAddToCart() throws SQLException, DaoException {
		new CartDaoImpl().addToCart("cus1", "Crop", 3);
	}
	
	@Test
	public void testCheckOutCart() throws SQLException, DaoException {
		new CartDaoImpl().checkOutCart("cus1");
	}

}