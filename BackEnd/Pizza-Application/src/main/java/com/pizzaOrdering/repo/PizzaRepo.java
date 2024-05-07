package com.pizzaOrdering.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pizzaOrdering.model.Pizza;


@Repository
public interface PizzaRepo extends JpaRepository<Pizza,String> {

}
