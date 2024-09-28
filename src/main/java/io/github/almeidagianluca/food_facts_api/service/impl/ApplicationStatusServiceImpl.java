package io.github.almeidagianluca.food_facts_api.service.impl;

import io.github.almeidagianluca.food_facts_api.model.ApplicationStatus;
import io.github.almeidagianluca.food_facts_api.model.CronImport;
import io.github.almeidagianluca.food_facts_api.repository.CronImportRepository;
import io.github.almeidagianluca.food_facts_api.service.ApplicationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.SimpleDateFormat;

import static io.github.almeidagianluca.food_facts_api.utils.ConvertUtils.*;

@Service
public class ApplicationStatusServiceImpl implements ApplicationStatusService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CronImportRepository cronImportRepository;

    @Override
    public ApplicationStatus getApplicationStatus() {
        return ApplicationStatus.builder()
                .isMongoDbConnected(isDatabaseConnected())
                .upTimeSeconds(getUptime())
                .memoryUsageMB(getMemoryUsage())
                .lastCronRunDate(geLastCronImport())
                .build();
    }

    private boolean isDatabaseConnected() {
        try {
            mongoTemplate.executeCommand("{ ping: 1 }");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    private long getUptime() {
        RuntimeMXBean mxBean = ManagementFactory.getRuntimeMXBean();
        return millisecondsToSeconds(mxBean.getUptime());
    }

    private long getMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        long memoryUsed = (runtime.totalMemory() - runtime.freeMemory());
        return bytesToMegabytes(memoryUsed);
    }

    private String geLastCronImport() {
        CronImport lastCronImport = cronImportRepository.findTopByOrderByDateDesc();
        return dateToString(lastCronImport.getDate());
    }
}
