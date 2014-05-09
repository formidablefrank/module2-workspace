package com.example;

import java.math.BigDecimal;

public class Espresso implements Coffee {

	@Override
	public BigDecimal getPrice() {
		return new BigDecimal("100.00");
	}

	@Override
	public String getDescription() {
		return "Espresso";
	}

}
