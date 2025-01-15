package com.pizzaOrdering.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.pizzaOrdering.exception.PizzaNotFoundException;
import com.pizzaOrdering.model.Pizza;
import com.pizzaOrdering.model.Type;
import com.pizzaOrdering.payload.PizzaDTO;
import com.pizzaOrdering.repo.PizzaRepo;
import com.pizzaOrdering.service.PizzaServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PizzaServiceImplTest {

	@Mock
	private PizzaRepo pizzaRepo;

	@InjectMocks
	private PizzaServiceImpl pizzaService;

	private Pizza pizza;
	private PizzaDTO pizzaDTO;

	@BeforeEach
	public void setUp() {
		pizza = Pizza.builder().id("abcd").name("Margherita").description("Cheese Pizza").size("Medium").price(500)
				.type(Type.VEG).build();
		pizzaDTO = PizzaDTO.builder().pizzaId("abcd").name("Margherita").description("Cheese Pizza").size("Medium")
				.price(500).type(Type.VEG).build();
	}

	@Test
	@DisplayName("Get Single Pizza by ID")
	@Order(1)
	public void testGetSinglePizza() {
	    when(pizzaRepo.findById("abcd")).thenReturn(Optional.of(pizza));
	    PizzaDTO response = pizzaService.getSinglePizza("abcd");
//	    assertThat(response.getPizzaId()).isEqualTo("abcd");
	    assertThat(response.getName()).isEqualTo("Margherita");
	    assertThat(response.getSize()).isEqualTo("Medium");
	    assertThat(response.getPrice()).isEqualTo(500);
	    assertThat(response.getType()).isEqualTo(Type.VEG);
	    verify(pizzaRepo, times(1)).findById("abcd");
	}

	@Test
	    @DisplayName("Get Single Pizza by ID - Not Found")
	    @Order(2)
	public  void testGetSinglePizzaNotFound() {
	        when(pizzaRepo.findById("abcd")).thenReturn(Optional.empty());
	        PizzaNotFoundException exception = assertThrows(PizzaNotFoundException.class, () -> pizzaService.getSinglePizza("abcd"));
	        assertThat(exception.getMessage()).isEqualTo("Pizza not found with id : abcd");
	        verify(pizzaRepo, times(1)).findById("abcd");
	    }

	@Test
	@DisplayName("Get All Pizzas")
	@Order(3)
	public void testGetAllPizza() {
		List<Pizza> pizzas = new ArrayList<>();
		pizzas.add(pizza);
		pizzas.add(Pizza.builder().id("efgh").name("Pepperoni").description("Spicy Pizza").size("Large").price(800)
				.type(Type.NON_VEG).build());

		when(pizzaRepo.findAll()).thenReturn(pizzas);

		List<PizzaDTO> result = pizzaService.getAllPizza();

		assertThat(result).hasSize(2);
		assertThat(result.get(0).getName()).isEqualTo("Margherita");
		assertThat(result.get(1).getName()).isEqualTo("Pepperoni");
		verify(pizzaRepo, times(1)).findAll();
	}

	@Test
	@DisplayName("Add Pizza")
	@Order(4)
	public void testAddPizza() {
		pizza = Pizza.builder().name("Margherita").description("Cheese Pizza").size("Medium").price(500).type(Type.VEG)
				.build();
		when(pizzaRepo.save(pizza)).thenReturn(pizza);
		PizzaDTO response = pizzaService.addPizza(pizzaDTO);
		assertThat(response).isNotNull();
		assertThat(response.getName()).isEqualTo("Margherita");
		verify(pizzaRepo, times(1)).save(any(Pizza.class));
	}

	@Test
 @DisplayName("Delete Pizza by ID")
 public void testDeletePizzaByPizzaId() {
	        when(pizzaRepo.findById("abcd")).thenReturn(Optional.of(pizza));
	        String result = pizzaService.deletePizzaByPizzaId("abcd");
	        assertThat(result).isEqualTo("pizza deleted successfully");
	        verify(pizzaRepo, times(1)).findById("abcd");
	    }

	@Test
	    @DisplayName("Delete Pizza by ID - Not Found")
	    public void testDeletePizzaByPizzaIdNotFound() {
	        when(pizzaRepo.findById("abcd")).thenReturn(Optional.empty());
	        PizzaNotFoundException exception = assertThrows(PizzaNotFoundException.class, () -> pizzaService.deletePizzaByPizzaId("abcd"));
	        assertThat(exception.getMessage()).isEqualTo("Pizza not found with id : abcd");
	        verify(pizzaRepo, times(1)).findById("abcd");
	    }

	@Test
	    @DisplayName("Update Pizza by ID")
	    public void testUpdatePizzaByPizzaId() {
	        when(pizzaRepo.findById("abcd")).thenReturn(Optional.of(pizza));  
	        Pizza updatedPizza = Pizza.builder().id("abcd").name("Updated Pizza").description("Updated Pizza").size("Large").price(900)
	                .type(Type.VEG).build();
	        PizzaDTO updatedPizzadto = PizzaDTO.builder().name("Updated Pizza").description("Updated Pizza").size("Large").price(900)
	                .type(Type.VEG).build();
	        when(pizzaRepo.save(updatedPizza)).thenReturn(updatedPizza);
	        String response = pizzaService.updatePizzaByPizzaId("abcd", updatedPizzadto);
	        assertEquals(response, "Pizza updated successfully");
	    }

	@Test
	@DisplayName("Search Pizza by Type")
	void testSearchPizzaByType() {
		List<Pizza> pizzas = new ArrayList<>();
		pizzas.add(pizza);
		when(pizzaRepo.findByType(Type.VEG)).thenReturn(pizzas);
		List<PizzaDTO> result = pizzaService.searchPizzaByType(Type.VEG);
		assertThat(result).hasSize(1);
		assertThat(result.get(0).getName()).isEqualTo("Margherita");
		verify(pizzaRepo, times(1)).findByType(Type.VEG);
	}

	@Test
	@DisplayName("Search Pizza by Price Less Than or Equal To")
	void testSearchPizzaByPriceLessThan() {
		List<Pizza> pizzas = new ArrayList<>();
		pizzas.add(pizza);
		when(pizzaRepo.findByPriceLessThanEqual(600)).thenReturn(pizzas);
		List<PizzaDTO> result = pizzaService.searchPizzaByPrizeLessThan(600);
		assertThat(result).hasSize(1);
		assertThat(result.get(0).getName()).isEqualTo("Margherita");
		verify(pizzaRepo, times(1)).findByPriceLessThanEqual(600);
	}
}