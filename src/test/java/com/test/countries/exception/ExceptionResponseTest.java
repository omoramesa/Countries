package com.test.countries.exception;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExceptionResponseTest {

    @Test
    public void testGetMessage() {
        String expectedMessage = "Test message";
        ExceptionResponse exceptionResponse = new ExceptionResponse(expectedMessage);
        String actualMessage = exceptionResponse.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testSetMessage() {
        String expectedMessage = "Test message";
        ExceptionResponse exceptionResponse = new ExceptionResponse("");
        exceptionResponse.setMessage(expectedMessage);
        String actualMessage = exceptionResponse.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}