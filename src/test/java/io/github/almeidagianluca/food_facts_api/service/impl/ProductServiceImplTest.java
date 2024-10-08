package io.github.almeidagianluca.food_facts_api.service.impl;

import com.mongodb.client.result.UpdateResult;
import io.github.almeidagianluca.food_facts_api.model.Product;
import io.github.almeidagianluca.food_facts_api.model.ProductStatus;
import io.github.almeidagianluca.food_facts_api.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

import static io.github.almeidagianluca.food_facts_api.mocks.ProductMock.getProductMock;
import static io.github.almeidagianluca.food_facts_api.mocks.ProductMock.getProductsListMock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private MongoTemplate mongoTemplate;

    @Test
    void getProducts() {
        List<Product> mockProductsList = getProductsListMock();
        Page<Product> mockPage = new PageImpl<>(mockProductsList, PageRequest.of(0, 10), mockProductsList.size());
        when(productRepository.findAll(any(Pageable.class))).thenReturn(mockPage);

        Page<Product> result = productService.getProducts(0, 10);

        assertEquals(2, result.getContent().size());
        assertEquals(mockProductsList, result.getContent());
    }

    @Test
    void getProductByCode() {
        Product mockProductList = getProductMock();
        String code = "123";
        when(productRepository.findByCode(code)).thenReturn(mockProductList);

        Product result = productService.getProductByCode(code);

        assertEquals(mockProductList, result);
    }

    @Test
    public void updateProductSuccess() {
        Product productMock = getProductMock();
        productMock.setProductName("Updated Product");

        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.getMatchedCount()).thenReturn(1L);
        when(mongoTemplate.updateFirst(any(Query.class), any(Update.class), eq(Product.class)))
                .thenReturn(updateResult);

        ResponseEntity<Product> response = productService.updateProduct("123", productMock);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productMock, response.getBody());
    }

    @Test
    public void updateProductNotFound() {
        Product productMock = getProductMock();
        productMock.setProductName("Updated Product");

        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.getMatchedCount()).thenReturn(0L);
        when(mongoTemplate.updateFirst(any(Query.class), any(Update.class), eq(Product.class)))
                .thenReturn(updateResult);

        assertThrows(ResponseStatusException.class, () -> productService.updateProduct("123", productMock));
    }

    @Test
    public void deleteProduct() {
        Product productMock = getProductMock();

        UpdateResult updateResult = mock(UpdateResult.class);
        when(productRepository.findByCode("123")).thenReturn(productMock);
        when(updateResult.getMatchedCount()).thenReturn(1L);
        when(mongoTemplate.updateFirst(any(Query.class), any(Update.class), eq(Product.class)))
                .thenReturn(updateResult);

        ResponseEntity<Product> response = productService.deleteProduct("123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ProductStatus.TRASH, Objects.requireNonNull(response.getBody()).getStatus());
    }
}