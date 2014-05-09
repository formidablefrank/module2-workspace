package com.example;

public class Runner {

	public static void main(String[] args) {
		Coffee coffee = new Espresso();
		coffee = new WhippedCream(coffee);
		coffee = new WhippedCream(coffee);
		coffee = new WhippedCream(coffee);
		coffee = new WhippedCream(coffee);
		coffee = new WhippedCream(coffee);
		System.out.println(coffee.getDescription());
		System.out.println(coffee.getPrice());
	}

}
