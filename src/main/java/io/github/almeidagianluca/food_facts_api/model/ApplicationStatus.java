package io.github.almeidagianluca.food_facts_api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationStatus {
    private boolean isMongoDbConnected;
    private long upTimeSeconds;
    private long memoryUsageMB;
    private String lastCronRunDate;

}

