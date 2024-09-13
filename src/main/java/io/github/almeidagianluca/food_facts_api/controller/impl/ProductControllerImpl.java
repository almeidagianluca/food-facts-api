package io.github.almeidagianluca.food_facts_api.controller.impl;

import io.github.almeidagianluca.food_facts_api.controller.ProductController;
import io.github.almeidagianluca.food_facts_api.model.Product;
import io.github.almeidagianluca.food_facts_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductControllerImpl implements ProductController {

    @Autowired
    private ProductService productService;

    @Override
    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @Override
    @GetMapping("/{code}")
    public Product getProductByCode(@PathVariable Integer code) {
        return productService.getProductByCode(code);
    }

    @Override
    @PutMapping("/{code}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer code, @RequestBody Product product) {
        return productService.updateProduct(code, product);
    }

    @Override
    @DeleteMapping("/{code}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer code) {
        return productService.deleteProduct(code);
    }
}
