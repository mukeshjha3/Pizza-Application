package com.pizzaOrdering.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PizzaNotFoundException extends RuntimeException{

	private String id;
	
	
	
}
