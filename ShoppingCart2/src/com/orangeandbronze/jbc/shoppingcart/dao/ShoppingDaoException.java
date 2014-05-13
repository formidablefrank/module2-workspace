package com.orangeandbronze.jbc.shoppingcart.dao;

public class ShoppingDaoException extends Exception {

	/**
	 * Thrown if there is an error accessing the products and cart files.
	 * Wraps IOException.
	 */
	
	private static final long serialVersionUID = 7730451885140529678L;

	public ShoppingDaoException() {
	}

	public ShoppingDaoException(String message) {
		super(message);
	}

	public ShoppingDaoException(Throwable cause) {
		super(cause);
	}

	public ShoppingDaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ShoppingDaoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
