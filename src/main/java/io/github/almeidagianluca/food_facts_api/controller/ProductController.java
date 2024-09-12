package io.github.almeidagianluca.food_facts_api.controller;

import io.github.almeidagianluca.food_facts_api.model.Product;

import java.util.List;

public interface ProductController {
    List<Product> getProducts();
}
