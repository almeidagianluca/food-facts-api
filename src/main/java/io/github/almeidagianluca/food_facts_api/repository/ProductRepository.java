package io.github.almeidagianluca.food_facts_api.repository;

import io.github.almeidagianluca.food_facts_api.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {
    Product findByCode(Integer code);
}
