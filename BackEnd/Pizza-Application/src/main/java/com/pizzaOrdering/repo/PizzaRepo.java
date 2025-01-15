package com.pizzaOrdering.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pizzaOrdering.model.Pizza;
import com.pizzaOrdering.model.Type;

@Repository
public interface PizzaRepo extends JpaRepository<Pizza, String> {

	List<Pizza> findByType(Type type);
	List<Pizza> findByPriceLessThanEqual(int price);
}
