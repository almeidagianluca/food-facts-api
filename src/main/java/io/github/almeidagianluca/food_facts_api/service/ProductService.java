package io.github.almeidagianluca.food_facts_api.service;

import io.github.almeidagianluca.food_facts_api.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product getProductByCode(Integer code);
    ResponseEntity<Product> updateProduct(Integer code, Product updatedProduct);
    ResponseEntity<Product> deleteProduct(Integer code);
}
