package io.github.almeidagianluca.food_facts_api.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "products")
@Builder
public class Product {
    private String code;
    private ProductStatus status;
    @SerializedName("imported_t")
    private Date importedT;
    private String url;
    private String creator;
    @SerializedName("created_t")
    private long createdT;
    @SerializedName("last_modified_t")
    private long lastModifiedT;
    @SerializedName("product_name")
    private String productName;
    private String quantity;
    private String brands;
    private String categories;
    private String labels;
    private String cities;
    @SerializedName("purchase_places")
    private String purchasePlaces;
    private String stores;
    @SerializedName("ingredients_text")
    private String ingredientsText;
    private String traces;
    @SerializedName("serving_size")
    private String servingSize;

    @SerializedName("nutriscore_grade")
    private String nutriscoreGrade;
    @SerializedName("main_category")
    private String mainCategory;
    @SerializedName("image_url")
    private String imageUrl;
}
