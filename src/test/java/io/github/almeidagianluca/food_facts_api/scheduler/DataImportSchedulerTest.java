package io.github.almeidagianluca.food_facts_api.scheduler;

import io.github.almeidagianluca.food_facts_api.service.DataImportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DataImportSchedulerTest {
    @InjectMocks
    private DataImportScheduler dataImportScheduler;

    @Mock
    private DataImportService dataImportService;

    @Test
    void testScheduleDataImport() {
        dataImportScheduler.scheduleDataImport();

        verify(dataImportService).importData();
    }
}