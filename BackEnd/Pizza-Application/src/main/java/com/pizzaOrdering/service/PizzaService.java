package com.pizzaOrdering.service;

import java.util.List;

import com.pizzaOrdering.model.Pizza;
import com.pizzaOrdering.model.Type;
import com.pizzaOrdering.payload.PizzaDTO;

public interface PizzaService {

	public PizzaDTO getSinglePizza(String id);
	public List<PizzaDTO> getAllPizza();
	public PizzaDTO addPizza(PizzaDTO pizzadto);
	public String deletePizzaByPizzaId(String pizzaId);
	public String updatePizzaByPizzaId(String pizzaId, PizzaDTO pizzaDTO);
    public List<PizzaDTO> searchPizzaByType(Type type);
    public List<PizzaDTO> searchPizzaByPrizeLessThan(int prize);
}
