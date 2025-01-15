package com.pizzaOrdering.payload;

import com.pizzaOrdering.model.Type;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PizzaDTO {

	private String pizzaId;
	private String name;
	private String description;
	private String size;
	private int price;
	private Type type;

}
