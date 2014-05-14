package com.example.service;

import com.example.model.Cart;
import com.example.model.Inventory;

public interface CustomerService {
	Inventory viewProducts();
	void addToCart(String productName, int quantity);
	void removeFromCart(String productName, int quantity);
	void clearCart(String userName);
	Cart viewCart();
	void checkOutCart(String username);
}
