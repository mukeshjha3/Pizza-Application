package com.pizzaOrdering.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaOrdering.Controller.PizzaController;
import com.pizzaOrdering.model.Type;
import com.pizzaOrdering.payload.PizzaDTO;
import com.pizzaOrdering.service.PizzaService;

@WebMvcTest(controllers = PizzaController.class)
@DisplayName("PizzaController Test Cases")
public class PizzaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PizzaService pizzaService;

	private ObjectMapper objectMapper;

	private PizzaDTO pizzaDTO;

	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
		pizzaDTO = PizzaDTO.builder().pizzaId("1").name("Margherita").description("Classic cheese pizza").size("Medium")
				.price(10).type(Type.VEG).build();
	}

	@Test
	void testGetAllPizza() throws Exception {
		List<PizzaDTO> pizzas = Arrays.asList(pizzaDTO);
		when(pizzaService.getAllPizza()).thenReturn(pizzas);
		mockMvc.perform(get("/pizza/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(pizzas.size()))
				.andExpect(jsonPath("$[0].name").value("Margherita"));
	}

	@Test
    void testGetSinglePizza() throws Exception {
        when(pizzaService.getSinglePizza("1")).thenReturn(pizzaDTO);
        mockMvc.perform(get("/pizza/{pizzaId}", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pizzaId").value("1"))
                .andExpect(jsonPath("$.name").value("Margherita"));
    }

	@Test
    void testAddPizza() throws Exception {
        when(pizzaService.addPizza(any(PizzaDTO.class))).thenReturn(pizzaDTO);

        mockMvc.perform(post("/pizza/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pizzaDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Margherita"));
    }

	@Test
    void testDeletePizzaByPizzaId() throws Exception {
        when(pizzaService.deletePizzaByPizzaId("1")).thenReturn("Pizza deleted successfully!");

        mockMvc.perform(delete("/pizza/{pizzaId}", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Pizza deleted successfully!"));
    }

	@Test
    void testUpdatePizzaByPizzaId() throws Exception {
        when(pizzaService.updatePizzaByPizzaId(eq("1"), any(PizzaDTO.class)))
                .thenReturn("Pizza updated successfully!");

        mockMvc.perform(put("/pizza/{pizzaId}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pizzaDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Pizza updated successfully!"));
    }

	@Test
	void testSearchPizzaByType() throws Exception {
		List<PizzaDTO> pizzas = Arrays.asList(pizzaDTO);

		when(pizzaService.searchPizzaByType(Type.VEG)).thenReturn(pizzas);

		mockMvc.perform(get("/pizza/type/{type}", Type.VEG).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(pizzas.size()))
				.andExpect(jsonPath("$[0].type").value("VEG"));
	}

	@Test
	void testSearchPizzaByPrice() throws Exception {
		List<PizzaDTO> pizzas = Arrays.asList(pizzaDTO);
		when(pizzaService.searchPizzaByPrizeLessThan(15)).thenReturn(pizzas);
		mockMvc.perform(get("/pizza/price/{price}", 15).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(pizzas.size()))
				.andExpect(jsonPath("$[0].price").value(10));
	}
}
