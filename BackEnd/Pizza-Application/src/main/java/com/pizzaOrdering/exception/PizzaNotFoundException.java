package com.pizzaOrdering.exception;

import lombok.AllArgsConstructor;

@SuppressWarnings("serial")
public class PizzaNotFoundException extends RuntimeException {

	private String resourceId;

	public PizzaNotFoundException(String resourceId) {
		super("Pizza not found with id : " + resourceId);
		this.resourceId = resourceId;
	}

}
