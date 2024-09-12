package io.github.almeidagianluca.food_facts_api.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "products")
@Builder
public class Product {
    private Integer code;
    private String status;
    private String importedT;
    private String url;
    private String creator;
    @Field(name = "created_t")
    private long createdT;
    @Field(name = "last_modified_t")
    private long lastModifiedT;
    private String productName;
    private String quantity;
    private String brands;
    private String categories;
    private String labels;
    private String cities;
}
