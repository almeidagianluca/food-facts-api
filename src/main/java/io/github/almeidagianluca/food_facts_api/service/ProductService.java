package io.github.almeidagianluca.food_facts_api.service;

import io.github.almeidagianluca.food_facts_api.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    Page<Product> getProducts(int page, int size);
    Product getProductByCode(String code);
    ResponseEntity<Product> updateProduct(String code, Product updatedProduct);
    ResponseEntity<Product> deleteProduct(String code);
}
