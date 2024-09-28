package io.github.almeidagianluca.food_facts_api.service.impl;

import io.github.almeidagianluca.food_facts_api.model.ApplicationStatus;
import io.github.almeidagianluca.food_facts_api.model.CronImport;
import io.github.almeidagianluca.food_facts_api.repository.CronImportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationStatusServiceImplTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @Mock
    private CronImportRepository cronImportRepository;

    @InjectMocks
    private ApplicationStatusServiceImpl applicationStatusService;

    @Test
    void getApplicationStatusWhenMongoIsConnected() {
        CronImport cronImport = CronImport.builder()
                .status("success")
                .date(new Date())
                .build();
        when(cronImportRepository.findTopByOrderByDateDesc()).thenReturn(cronImport);

        ApplicationStatus status = applicationStatusService.getApplicationStatus();

        assertTrue(status.isMongoDbConnected());
        assertNotNull(status.getLastCronRunDate());
        assertTrue(status.getUpTimeSeconds() > 0);
        assertTrue(status.getMemoryUsageMB() > 0);
    }

    @Test
    void getApplicationStatusWhenMongoIsNotConnected() {
        CronImport cronImport = CronImport.builder()
                .status("success")
                .date(new Date())
                .build();
        when(cronImportRepository.findTopByOrderByDateDesc()).thenReturn(cronImport);
        when(mongoTemplate.executeCommand("{ ping: 1 }")).thenThrow(RuntimeException.class);

        ApplicationStatus status = applicationStatusService.getApplicationStatus();

        assertFalse(status.isMongoDbConnected());
    }
}