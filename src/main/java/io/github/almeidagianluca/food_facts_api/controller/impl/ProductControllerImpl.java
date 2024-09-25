package io.github.almeidagianluca.food_facts_api.controller.impl;

import io.github.almeidagianluca.food_facts_api.controller.ProductController;
import io.github.almeidagianluca.food_facts_api.model.Product;
import io.github.almeidagianluca.food_facts_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductControllerImpl implements ProductController {

    @Autowired
    private ProductService productService;

    @Override
    @GetMapping
    public Page<Product> getProducts(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        return productService.getProducts(page, size);
    }

    @Override
    @GetMapping("/{code}")
    public Product getProductByCode(@PathVariable String code) {
        return productService.getProductByCode(code);
    }

    @Override
    @PutMapping("/{code}")
    public ResponseEntity<Product> updateProduct(@PathVariable String code, @RequestBody Product product) {
        return productService.updateProduct(code, product);
    }

    @Override
    @DeleteMapping("/{code}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String code) {
        return productService.deleteProduct(code);
    }
}
