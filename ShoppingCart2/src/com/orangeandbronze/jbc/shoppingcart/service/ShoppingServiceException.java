package com.orangeandbronze.jbc.shoppingcart.service;

public class ShoppingServiceException extends Exception {

	/**
	 * Throws when violations of user occur:
	 * (1) User buy products that does not exist
	 * (2) User buy more products than that being sold
	 * (3) User buy products with negative quantity
	 * (4) User buy products with zero quantity
	 * 
	 * Messages are shown to user using the String parameter of this exception.
	 */
	
	private static final long serialVersionUID = 7730451885140529678L;

	public ShoppingServiceException() {
	}

	public ShoppingServiceException(String message) {
		super(message);
	}

	public ShoppingServiceException(Throwable cause) {
		super(cause);
	}

	public ShoppingServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ShoppingServiceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
