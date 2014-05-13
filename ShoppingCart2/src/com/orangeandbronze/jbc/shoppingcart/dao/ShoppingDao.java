package com.orangeandbronze.jbc.shoppingcart.dao;

import java.util.List;

import com.orangeandbronze.jbc.shoppingcart.model.CartProduct;
import com.orangeandbronze.jbc.shoppingcart.model.InventoryProduct;

public interface ShoppingDao {
	List<InventoryProduct> getInventory() throws ShoppingDaoException;
	List<CartProduct> getCart() throws ShoppingDaoException;
	void updateInventory(List<InventoryProduct> collection) throws ShoppingDaoException;
	void updateCart(List<CartProduct> collection) throws ShoppingDaoException;
}