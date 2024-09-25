package io.github.almeidagianluca.food_facts_api.scheduler;

import io.github.almeidagianluca.food_facts_api.service.DataImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class DataImportScheduler {
    @Autowired
    private DataImportService dataImportService;


    public void scheduleDataImport() {
        dataImportService.importData();
    }
}
