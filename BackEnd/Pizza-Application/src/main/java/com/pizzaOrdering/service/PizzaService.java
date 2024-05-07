package com.pizzaOrdering.service;

import java.util.List;

import com.pizzaOrdering.model.Pizza;
import com.pizzaOrdering.payload.PizzaRequest;

public interface PizzaService {

	public Pizza getSinglePizza(String id);
	public List< Pizza> getAllPizza();
	public Pizza addPizza(PizzaRequest items);
}
