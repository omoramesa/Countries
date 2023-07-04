package com.test.countries.Services;

import com.test.countries.models.Log;
import com.test.countries.repositories.LogRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;

//Prueba de git
public class LogServiceTest {

    private LogService logService;

    @Mock
    private LogRepository logRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        logService = new LogService(logRepository);
    }

    @Test
    public void testSaveLog() {
        String expectedName = "Test Name";
        String expectedUrl = "http://test.com";
        LocalDateTime expectedTimestamp = LocalDateTime.now();

        logService.saveLog(expectedName, expectedUrl);

        ArgumentCaptor<Log> logCaptor = ArgumentCaptor.forClass(Log.class);
        Mockito.verify(logRepository).save(logCaptor.capture());
        Log capturedLog = logCaptor.getValue();

        Assertions.assertEquals(expectedName, capturedLog.getCountry());
        Assertions.assertEquals(expectedUrl, capturedLog.getUrl());
        Assertions.assertEquals(expectedTimestamp.getMinute(), capturedLog.getTimestamp().getMinute());
    }
}