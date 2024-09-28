package io.github.almeidagianluca.food_facts_api.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "imports")
@Builder
public class CronImport {
    private Date date;
    private String status;
}
