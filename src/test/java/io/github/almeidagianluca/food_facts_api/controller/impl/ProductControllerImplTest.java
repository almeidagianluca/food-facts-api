package io.github.almeidagianluca.food_facts_api.controller.impl;

import com.google.gson.Gson;
import io.github.almeidagianluca.food_facts_api.model.Product;
import io.github.almeidagianluca.food_facts_api.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static io.github.almeidagianluca.food_facts_api.mocks.ProductMock.getProductMock;
import static io.github.almeidagianluca.food_facts_api.mocks.ProductMock.getProductsListMock;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResponse));
    }

    @Test
    void getProductByCode() throws Exception {
        Product productMock = getProductMock();
        Integer code = 123;
        Gson gson = new Gson();
        String expectedResponse = gson.toJson(productMock);
        when(productService.getProductByCode(code)).thenReturn(productMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/123")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResponse));
    }

    @Test
    public void updateProduct() throws Exception {
        Product productMock = getProductMock();

        when(productService.updateProduct(123, productMock))
                .thenReturn(new ResponseEntity<>(productMock, HttpStatus.OK));

        Gson gson = new Gson();
        String expectedResponse = gson.toJson(productMock);

        mockMvc.perform(MockMvcRequestBuilders.put("/products/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(expectedResponse))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResponse));;
    }

    @Test
    void deleteProduct() throws Exception {
        Product productMock = getProductMock();
        Integer code = 123;
        Gson gson = new Gson();
        String expectedResponse = gson.toJson(productMock);
        when(productService.deleteProduct(code)).thenReturn(new ResponseEntity<>(productMock, HttpStatus.OK));

        mockMvc.perform(MockMvcRequestBuilders.delete("/products/123")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResponse));
    }
}