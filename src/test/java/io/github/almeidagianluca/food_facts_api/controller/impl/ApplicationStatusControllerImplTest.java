package io.github.almeidagianluca.food_facts_api.controller.impl;

import io.github.almeidagianluca.food_facts_api.model.ApplicationStatus;
import io.github.almeidagianluca.food_facts_api.service.ApplicationStatusService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationStatusControllerImplTest {

    @Mock
    private ApplicationStatusService applicationStatusService;

    @InjectMocks
    private ApplicationStatusControllerImpl applicationStatusController;

    @Test
    void getApplicationStatus() {
        ApplicationStatus mockStatus = ApplicationStatus.builder()
                .isMongoDbConnected(true)
                .upTimeSeconds(1000L)
                .memoryUsageMB(512L)
                .lastCronRunDate("2024-09-27")
                .build();
        when(applicationStatusService.getApplicationStatus()).thenReturn(mockStatus);

        ApplicationStatus status = applicationStatusController.getApplicationStatus();

        verify(applicationStatusService, times(1)).getApplicationStatus();
        assertNotNull(status);
        assertTrue(status.isMongoDbConnected());
        assertEquals(1000L, status.getUpTimeSeconds());
        assertEquals(512L, status.getMemoryUsageMB());
        assertEquals("2024-09-27", status.getLastCronRunDate());
    }
}