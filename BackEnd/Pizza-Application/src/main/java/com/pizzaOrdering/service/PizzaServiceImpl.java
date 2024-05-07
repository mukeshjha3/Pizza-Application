package com.pizzaOrdering.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pizzaOrdering.exception.PizzaNotFoundException;
import com.pizzaOrdering.model.Pizza;
import com.pizzaOrdering.payload.PizzaRequest;
import com.pizzaOrdering.repo.PizzaRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PizzaServiceImpl implements PizzaService {
	
	private final PizzaRepo pizzaRepo;

	@Override
	public Pizza getSinglePizza(String id) {
		Optional<Pizza> findById = pizzaRepo.findById(id);
		Pizza pizza = findById.orElseThrow(() -> new PizzaNotFoundException(id));
		return pizza;
	}

	@Override
	public List<Pizza> getAllPizza() {
		List<Pizza> allPizza = pizzaRepo.findAll();
		return allPizza;
	}

	@Override
	public Pizza addPizza(PizzaRequest items) {
		Pizza pizza = PizzaRequestEntityToPizzaEntity(items);
		Pizza pizza1 = pizzaRepo.save(pizza);
		return pizza1;
	}
	
	
	public Pizza PizzaRequestEntityToPizzaEntity(PizzaRequest pizzaRequest) {
		Pizza pizza= new Pizza();
		pizza.setId(UUID.randomUUID().toString());
		pizza.setProductName(pizzaRequest.getProductName());
		pizza.setProductSize(pizzaRequest.getProductSize());
		int intValue = pizzaRequest.getProductPrice();
		BigDecimal bigDecimalValue = new BigDecimal(intValue);
		pizza.setPrice(bigDecimalValue);
		pizza.setProductImageUrl(pizzaRequest.getProductImageUrl());
		return pizza;
		
	}

}
