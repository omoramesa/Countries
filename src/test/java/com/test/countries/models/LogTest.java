package com.test.countries.models;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class LogTest {

    @Test
    public void testGettersAndSetters() {
        Long expectedId = 1L;
        String expectedCountry = "Test Country";
        String expectedUrl = "http://test.com";
        LocalDateTime expectedTimestamp = LocalDateTime.now();

        Log log = new Log();
        log.setId(expectedId);
        log.setCountry(expectedCountry);
        log.setUrl(expectedUrl);
        log.setTimestamp(expectedTimestamp);

        Assertions.assertEquals(expectedId, log.getId());
        Assertions.assertEquals(expectedCountry, log.getCountry());
        Assertions.assertEquals(expectedUrl, log.getUrl());
        Assertions.assertEquals(expectedTimestamp, log.getTimestamp());
    }

    @Test
    public void testConstructorWithArguments() {
        String expectedCountry = "Test Country";
        String expectedUrl = "http://test.com";
        LocalDateTime expectedTimestamp = LocalDateTime.now();

        Log log = new Log(expectedCountry, expectedUrl, expectedTimestamp);

        Assertions.assertNull(log.getId());
        Assertions.assertEquals(expectedCountry, log.getCountry());
        Assertions.assertEquals(expectedUrl, log.getUrl());
        Assertions.assertEquals(expectedTimestamp, log.getTimestamp());
    }
}