package io.github.almeidagianluca.food_facts_api.service.impl;

import io.github.almeidagianluca.food_facts_api.model.Product;
import io.github.almeidagianluca.food_facts_api.repository.ProductRepository;
import io.github.almeidagianluca.food_facts_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
