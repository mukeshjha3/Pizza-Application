package com.pizzaOrdering.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach; // Use BeforeEach instead of BeforeAll for instance-level setup
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.pizzaOrdering.model.Pizza;
import com.pizzaOrdering.model.Type;
import com.pizzaOrdering.repo.PizzaRepo;

@DataJpaTest
@ActiveProfiles("test")
public class PizzaRepositoryTest {

	@Autowired
	private PizzaRepo pizzaRepo;

	private Pizza pizza;

	@BeforeEach
	public void setUp() {
		pizza = Pizza.builder().name("Custom Pizza").description("Custom Pizza with mozzarella and basil and chicken")
				.size("Extra Large").price(950).type(Type.NON_VEG).build();
	}

	@Test
	@DisplayName("Save Pizza Test")
	public void testSavePizza() {
		// Save the pizza to the in-memory database and get the saved pizza
		Pizza fetchedPizza = pizzaRepo.save(pizza);

		// Assertions to check if the pizza is saved correctly
		assertThat(fetchedPizza).isNotNull();
		assertThat(fetchedPizza.getName()).isEqualTo(pizza.getName());
		assertThat(fetchedPizza.getDescription()).isEqualTo(pizza.getDescription());
		assertThat(fetchedPizza.getSize()).isEqualTo(pizza.getSize());
		assertThat(fetchedPizza.getPrice()).isEqualTo(pizza.getPrice());
		assertThat(fetchedPizza.getType()).isEqualTo(pizza.getType());
	}

	@Test
	@DisplayName("Find By Id Test")
	public void testFindByIdTest() {
		Pizza savedPizza = pizzaRepo.save(pizza);
		Pizza fetchedPizza = pizzaRepo.findById(savedPizza.getId()).get();
		assertThat(fetchedPizza).isNotNull();
		assertThat(fetchedPizza.getName()).isEqualTo(pizza.getName());
		assertThat(fetchedPizza.getDescription()).isEqualTo(pizza.getDescription());
		assertThat(fetchedPizza.getSize()).isEqualTo(pizza.getSize());
		assertThat(fetchedPizza.getPrice()).isEqualTo(pizza.getPrice());
		assertThat(fetchedPizza.getType()).isEqualTo(pizza.getType());
	}

	@Test
	@DisplayName("Get All pizza")
	public void testGetAll() {
		Pizza pizza2 = Pizza.builder().name("Custom Pizza2")
				.description("Custom Pizza with mozzarella and basil and chicken").size(" Large").price(750)
				.type(Type.VEG).build();
		pizzaRepo.save(pizza);
		pizzaRepo.save(pizza2);
		List<Pizza> response = pizzaRepo.findAll();
		assertThat(response).size().isGreaterThanOrEqualTo(2);

	}

	@Test
	@DisplayName("Find By type")
	public void testFindByType() {
		Pizza pizza2 = Pizza.builder().name("Custom Pizza2")
				.description("Custom Pizza with mozzarella and basil and chicken").size(" Large").price(750)
				.type(Type.VEG).build();
		pizzaRepo.save(pizza);
		pizzaRepo.save(pizza2);
		List<Pizza> response = pizzaRepo.findByType(Type.NON_VEG);
		assertThat(response).size().isEqualTo(1);

	}
	
	@Test
	@DisplayName("Find By price less than or equal to")
	public void testFindByPriceLessThanEqual() {
		Pizza pizza2 = Pizza.builder().name("Custom Pizza2")
				.description("Custom Pizza with mozzarella and basil and chicken").size(" Large").price(550)
				.type(Type.VEG).build();
		pizzaRepo.save(pizza);
		pizzaRepo.save(pizza2);
		List<Pizza> response = pizzaRepo.findByPriceLessThanEqual(749);
		assertThat(response).size().isEqualTo(1);

	}

}
