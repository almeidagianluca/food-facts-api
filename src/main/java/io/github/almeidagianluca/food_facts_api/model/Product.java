package io.github.almeidagianluca.food_facts_api.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "products")
@Builder
public class Product {
    private Integer code;
    private String status;
    private String importedT;
    private String url;
    private String creator;
    private long createdT;
    private long lastModifiedT;
    private String productName;
    private String quantity;
    private String brands;
    private String categories;
    private String labels;
    private String cities;
}
