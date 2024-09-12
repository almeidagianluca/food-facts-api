package io.github.almeidagianluca.food_facts_api.mocks;

import io.github.almeidagianluca.food_facts_api.model.Product;

import java.util.List;

public class ProductMock {

    public static List<Product> getProductsListMock() {
        Product product1 = Product.builder()
                .code(123)
                .status("Available")
                .importedT("2024-09-12")
                .url("http://example.com/product")
                .creator("John Doe")
                .createdT(1631234567L)
                .lastModifiedT(1634567890L)
                .productName("Product Name")
                .quantity("1 kg")
                .brands("Brand A")
                .categories("Category 1")
                .labels("Label X")
                .cities("City Y")
                .build();
        Product product2 = Product.builder()
                .code(124)
                .status("Available")
                .importedT("2024-09-12")
                .url("http://example.com/product")
                .creator("John Doe")
                .createdT(1631234567L)
                .lastModifiedT(1634567890L)
                .productName("Product Name 2")
                .quantity("1 kg")
                .brands("Brand B")
                .categories("Category 2")
                .labels("Label X")
                .cities("City Y")
                .build();
        return List.of(product1, product2);
    }

    public static Product getProductMock() {
        return Product.builder()
                .code(123)
                .status("Available")
                .importedT("2024-09-12")
                .url("http://example.com/product")
                .creator("John Doe")
                .createdT(1631234567L)
                .lastModifiedT(1634567890L)
                .productName("Product Name")
                .quantity("1 kg")
                .brands("Brand A")
                .categories("Category 1")
                .labels("Label X")
                .cities("City Y")
                .build();
    }
}
