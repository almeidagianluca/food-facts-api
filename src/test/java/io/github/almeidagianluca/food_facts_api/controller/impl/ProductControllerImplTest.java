package io.github.almeidagianluca.food_facts_api.controller.impl;

import com.google.gson.Gson;
import io.github.almeidagianluca.food_facts_api.model.Product;
import io.github.almeidagianluca.food_facts_api.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static io.github.almeidagianluca.food_facts_api.mocks.ProductMock.getProductsListMock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(ProductControllerImpl.class)
class ProductControllerImplTest {
    @MockBean
    private ProductService productService;

    @InjectMocks
    private ProductControllerImpl productController;

    @Autowired
    private MockMvc mockMvc;
    @Test
    void getProducts() throws Exception {
        List<Product> mockProductsList = getProductsListMock();
        Gson gson = new Gson();
        String expectedResponse = gson.toJson(mockProductsList);
        when(productService.getProducts()).thenReturn(mockProductsList);

        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResponse));
    }
}