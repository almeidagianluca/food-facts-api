package io.github.almeidagianluca.food_facts_api.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.almeidagianluca.food_facts_api.model.Product;
import io.github.almeidagianluca.food_facts_api.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static io.github.almeidagianluca.food_facts_api.mocks.ProductMock.getProductMock;
import static io.github.almeidagianluca.food_facts_api.mocks.ProductMock.getProductsListMock;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductControllerImpl.class)
class ProductControllerImplTest {
    @MockBean
    private ProductService productService;

    @InjectMocks
    private ProductControllerImpl productController;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Test
    void getProducts() throws Exception {
        List<Product> mockProductsList = getProductsListMock();
        Page<Product> mockPage = new PageImpl<>(mockProductsList, PageRequest.of(0, 10), mockProductsList.size());
        when(productService.getProducts(anyInt(), anyInt())).thenReturn(mockPage);

        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .param("page", "0")
                        .param("size", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getProductByCode() throws Exception {
        Product productMock = getProductMock();
        String code = "123";
        String expectedResponse = objectMapper.writeValueAsString(productMock);
        when(productService.getProductByCode(code)).thenReturn(productMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/123")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResponse));
    }

    @Test
    public void updateProduct() throws Exception {
        Product productMock = getProductMock();

        when(productService.updateProduct("123", productMock))
                .thenReturn(new ResponseEntity<>(productMock, HttpStatus.OK));
        String expectedResponse = objectMapper.writeValueAsString(productMock);

        mockMvc.perform(MockMvcRequestBuilders.put("/products/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(expectedResponse))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResponse));
    }

    @Test
    void deleteProduct() throws Exception {
        Product productMock = getProductMock();
        String code = "123";
        String expectedResponse = objectMapper.writeValueAsString(productMock);
        when(productService.deleteProduct(code)).thenReturn(new ResponseEntity<>(productMock, HttpStatus.OK));

        mockMvc.perform(MockMvcRequestBuilders.delete("/products/123")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResponse));
    }
}