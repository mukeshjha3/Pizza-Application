package com.pizzaOrdering.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PizzaRequest {

	private String productName;
	private String productSize;
	private int productPrice;
	private String productImageUrl;
	
}
