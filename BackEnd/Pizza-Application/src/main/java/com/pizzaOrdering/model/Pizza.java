package com.pizzaOrdering.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pizza {

	@Id
	private String id;
	
	private String productName;
	
	private String productSize;
	
	private String productImageUrl;
	
	private BigDecimal price;

	
	
}
