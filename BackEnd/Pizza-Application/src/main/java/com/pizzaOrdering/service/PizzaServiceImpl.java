package com.pizzaOrdering.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pizzaOrdering.exception.PizzaNotFoundException;
import com.pizzaOrdering.model.Pizza;
import com.pizzaOrdering.model.Type;
import com.pizzaOrdering.payload.PizzaDTO;
import com.pizzaOrdering.repo.PizzaRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PizzaServiceImpl implements PizzaService {

	@Autowired
	private final PizzaRepo pizzaRepo;

	@Override
	public PizzaDTO getSinglePizza(String pizzaId) {
		Pizza pizza = pizzaRepo.findById(pizzaId).orElseThrow(() -> new PizzaNotFoundException(pizzaId));
		PizzaDTO response = pizzaToPizzaResponse(pizza);
		return response;
	}

	// get all pizza from the inventory
	@Override
	public List<PizzaDTO> getAllPizza() {
		List<Pizza> allPizza = pizzaRepo.findAll();
		return allPizza.stream().map(this::pizzaToPizzaResponse).collect(Collectors.toList());
	}

	// add pizza to the inventory
	@Override
	public PizzaDTO addPizza(PizzaDTO pizzadto) {
		Pizza pizza = PizzaRequestToPizza(pizzadto);
		Pizza savedPizza = pizzaRepo.save(pizza);
		PizzaDTO response = pizzaToPizzaResponse(savedPizza);
		return response;
	}

	@Override
	public String deletePizzaByPizzaId(String pizzaId) {
		Pizza pizza = pizzaRepo.findById(pizzaId).orElseThrow(() -> new PizzaNotFoundException(pizzaId));
//		pizzaRepo.delete(pizza);
		pizzaRepo.deleteById(pizzaId);
		return "pizza deleted successfully";
	}

	@Override
	public String updatePizzaByPizzaId(String pizzaId, PizzaDTO pizzaRequest) {
		Pizza existingPizza = pizzaRepo.findById(pizzaId).orElseThrow(() -> new PizzaNotFoundException(pizzaId));
		existingPizza.setName(pizzaRequest.getName());
		existingPizza.setDescription(pizzaRequest.getDescription());
		existingPizza.setSize(pizzaRequest.getSize());
		existingPizza.setPrice(pizzaRequest.getPrice());
		existingPizza.setType(pizzaRequest.getType());
		pizzaRepo.save(existingPizza);
		return "Pizza updated successfully";
	}

	public Pizza PizzaRequestToPizza(PizzaDTO pizzaRequest) {
		return Pizza.builder().description(pizzaRequest.getDescription()).name(pizzaRequest.getName())
				.size(pizzaRequest.getSize()).price(pizzaRequest.getPrice()).type(pizzaRequest.getType()).build();

	}

	public PizzaDTO pizzaToPizzaResponse(Pizza pizza) {
		return PizzaDTO.builder().description(pizza.getDescription()).size(pizza.getSize()).name(pizza.getName())
				.type(pizza.getType()).price(pizza.getPrice()).pizzaId(pizza.getId()).build();
	}

	@Override
	public List<PizzaDTO> searchPizzaByType(Type type) {
		List<Pizza> pizzas = pizzaRepo.findByType(type);
		return pizzas.stream().map((pizza) -> this.pizzaToPizzaResponse(pizza)).collect(Collectors.toList());

	}

	@Override
	public List<PizzaDTO> searchPizzaByPrizeLessThan(int prize) {
		List<Pizza> pizzas = pizzaRepo.findByPriceLessThanEqual(prize);
		return pizzas.stream().map((pizza) -> this.pizzaToPizzaResponse(pizza)).collect(Collectors.toList());

	}

}
