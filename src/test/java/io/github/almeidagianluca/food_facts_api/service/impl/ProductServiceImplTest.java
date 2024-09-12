package io.github.almeidagianluca.food_facts_api.service.impl;

import io.github.almeidagianluca.food_facts_api.model.Product;
import io.github.almeidagianluca.food_facts_api.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static io.github.almeidagianluca.food_facts_api.mocks.ProductMock.getProductMock;
import static io.github.almeidagianluca.food_facts_api.mocks.ProductMock.getProductsListMock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void getProducts() {
        List<Product> mockProducstList = getProductsListMock();

        when(productRepository.findAll()).thenReturn(mockProducstList);

        List<Product> result = productService.getProducts();

        assertEquals(2, result.size());
        assertEquals(mockProducstList, result);
    }

    @Test
    void getProductByCode() {
        Product mockProductList = getProductMock();
        Integer code = 123;
        when(productRepository.findByCode(code)).thenReturn(mockProductList);

        Product result = productService.getProductByCode(code);

        assertEquals(mockProductList, result);
    }
}