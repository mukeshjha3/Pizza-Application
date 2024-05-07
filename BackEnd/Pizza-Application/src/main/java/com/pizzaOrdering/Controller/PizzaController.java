package com.pizzaOrdering.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pizzaOrdering.model.Pizza;
import com.pizzaOrdering.payload.PizzaRequest;
import com.pizzaOrdering.service.PizzaService;

@RestController
@CrossOrigin("*")
public class PizzaController {

	
	@Autowired
	private  PizzaService pizzaService;
	

	@GetMapping("/getAllPizza")
	public ResponseEntity<List<Pizza>> getAllPizza() {
		List<Pizza> allPizza = pizzaService.getAllPizza();
		return new ResponseEntity<List<Pizza>>(allPizza, HttpStatus.OK);
	}

	@GetMapping("/getSinglePizza/{pizzaId}")
	public ResponseEntity<Pizza> getSinglePizza(@PathVariable String pizzaId) {
		Pizza singlePizza = pizzaService.getSinglePizza(pizzaId);
		return new ResponseEntity<Pizza>(singlePizza, HttpStatus.OK);
	}

	@PostMapping("/AddPizza")
	public ResponseEntity<Pizza> addPizza(@RequestBody PizzaRequest pizza) {
		System.out.println("inside the controller...");
		 Pizza addedPizza = pizzaService.addPizza(pizza);
		 return new ResponseEntity<Pizza>(addedPizza,HttpStatus.CREATED);
	}
}
