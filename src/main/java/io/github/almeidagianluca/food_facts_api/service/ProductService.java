package io.github.almeidagianluca.food_facts_api.service;

import io.github.almeidagianluca.food_facts_api.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product getProductByCode(Integer code);
}
