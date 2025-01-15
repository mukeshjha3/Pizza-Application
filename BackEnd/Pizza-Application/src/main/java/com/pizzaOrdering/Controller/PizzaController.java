package com.pizzaOrdering.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizzaOrdering.model.Pizza;
import com.pizzaOrdering.model.Type;
import com.pizzaOrdering.payload.PizzaDTO;
import com.pizzaOrdering.service.PizzaService;

import lombok.Delegate;

@RequestMapping("/pizza")
@RestController
@CrossOrigin("*")
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;

	@GetMapping("/")
	public ResponseEntity<List<PizzaDTO>> getAllPizza() {
		List<PizzaDTO> allPizza = pizzaService.getAllPizza();
		return new ResponseEntity<List<PizzaDTO>>(allPizza, HttpStatus.OK);
	}

	@GetMapping("/{pizzaId}")
	public ResponseEntity<PizzaDTO> getSinglePizza(@PathVariable String pizzaId) {
		PizzaDTO singlePizza = pizzaService.getSinglePizza(pizzaId);
		return new ResponseEntity<PizzaDTO>(singlePizza, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<PizzaDTO> addPizza(@RequestBody PizzaDTO pizza) {
		PizzaDTO response = pizzaService.addPizza(pizza);
		return new ResponseEntity<PizzaDTO>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/{pizzaId}")
	public ResponseEntity<?> deletePizzaByPizzaId(@PathVariable String pizzaId) {
		System.out.println("pizza id is "+ pizzaId);
		String deletePizzaByPizzaId = pizzaService.deletePizzaByPizzaId(pizzaId);
		return new ResponseEntity<String>(deletePizzaByPizzaId, HttpStatus.OK);

	}

	@PutMapping("/{pizzaId}")
	public ResponseEntity<?> updatePizzaByPizzaId(@PathVariable String pizzaId, @RequestBody PizzaDTO pizzadto) {
		String deletePizzaByPizzaId = pizzaService.updatePizzaByPizzaId(pizzaId, pizzadto);
		return new ResponseEntity<String>(deletePizzaByPizzaId, HttpStatus.OK);
	}
	
	 @GetMapping("/type/{type}")
	public ResponseEntity<?> searchPizzaByType(@PathVariable Type type){
		List<PizzaDTO> response = pizzaService.searchPizzaByType(type);
		return new ResponseEntity<List<PizzaDTO>>(response,HttpStatus.OK);
	}
	
	 @GetMapping("/price/{price}")
	 public ResponseEntity<?> searchPizzaByPrice(@PathVariable int price){
		 List<PizzaDTO> response = pizzaService.searchPizzaByPrizeLessThan(price);
		 return new ResponseEntity<List<PizzaDTO>> (response,HttpStatus.OK);
		 
	 }
}
