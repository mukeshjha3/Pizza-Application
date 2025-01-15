package com.pizzaOrdering.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalPizzaExceptionHandler {

	@ExceptionHandler(PizzaNotFoundException.class)
	public ResponseEntity<?> pizzaNotFoundExceptionHandler(PizzaNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
