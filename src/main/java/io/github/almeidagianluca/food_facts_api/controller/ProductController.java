package io.github.almeidagianluca.food_facts_api.controller;

import io.github.almeidagianluca.food_facts_api.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface ProductController {
    Page<Product> getProducts(int page, int size);
    Product getProductByCode(Integer code);
    ResponseEntity<Product> updateProduct(Integer code, Product updatedProduct);
    ResponseEntity<Product> deleteProduct(Integer code);
}
