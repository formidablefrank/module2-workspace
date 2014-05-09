package com.example;

import java.math.BigDecimal;

public class WhippedCream implements Coffee {

	private Coffee coffee;
	
	public WhippedCream(Coffee coffee) {
		super();
		this.coffee = coffee;
	}

	@Override
	public BigDecimal getPrice() {
		return coffee.getPrice().add(new BigDecimal("10.00"));
	}

	@Override
	public String getDescription() {
		return coffee.getDescription() + " with whipped cream";
	}

}
