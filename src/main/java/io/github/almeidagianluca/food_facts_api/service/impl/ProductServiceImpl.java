package io.github.almeidagianluca.food_facts_api.service.impl;

import com.mongodb.client.result.UpdateResult;
import io.github.almeidagianluca.food_facts_api.model.Product;
import io.github.almeidagianluca.food_facts_api.model.ProductStatus;
import io.github.almeidagianluca.food_facts_api.repository.ProductRepository;
import io.github.almeidagianluca.food_facts_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Page<Product> getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

    @Override
    public Product getProductByCode(String code) {
        return productRepository.findByCode(code);
    }

    @Override
    public ResponseEntity<Product> updateProduct(String code, Product updatedProduct) {
        Query query = new Query(Criteria.where("code").is(code));

        Update update = new Update()
                .set("status", updatedProduct.getStatus())
                .set("importedT", updatedProduct.getImportedT())
                .set("url", updatedProduct.getUrl())
                .set("creator", updatedProduct.getCreator())
                .set("createdT", updatedProduct.getCreatedT())
                .set("lastModifiedT", updatedProduct.getLastModifiedT())
                .set("productName", updatedProduct.getProductName())
                .set("quantity", updatedProduct.getQuantity())
                .set("brands", updatedProduct.getBrands())
                .set("categories", updatedProduct.getCategories())
                .set("labels", updatedProduct.getLabels())
                .set("cities", updatedProduct.getCities());

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Product.class);

        if (updateResult.getMatchedCount() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with code: " + code);
        }

        return ResponseEntity.ok(updatedProduct);
    }

    @Override
    public ResponseEntity<Product> deleteProduct(String code) {
        Product product = getProductByCode(code);
        product.setStatus(ProductStatus.TRASH);
        return updateProduct(code, product);
    }
}
