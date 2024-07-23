package com.onejava.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onejava.dto.ProductDto;
import com.onejava.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFilterProducts() throws Exception {
        List<ProductDto> expectedProducts = Arrays.asList(
                new ProductDto("Test Product 1", "Description 1", new BigDecimal("100.00")),
                new ProductDto( "Test Product 2", "Description 2", new BigDecimal("200.00"))
        );

        Mockito.when(productService.filterProducts(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(expectedProducts);

        mockMvc.perform(get("/products")
                        .param("name", "Test")
                        .param("minPrice", "50")
                        .param("maxPrice", "250"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedProducts)));
    }

    @Test
    public void noProductsFound_ShouldReturnEmptyList() throws Exception {
        List<ProductDto> expectedProducts = Collections.emptyList();

        Mockito.when(productService.filterProducts("NonExistent", null, null, null))
                .thenReturn(expectedProducts);

        mockMvc.perform(get("/products")
                        .param("name", "NonExistent"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedProducts)));
    }

    @Test
    public void invalidQueryParameters_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/products")
                        .param("minPrice", "notANumber"))
                .andExpect(status().isBadRequest());
    }
}