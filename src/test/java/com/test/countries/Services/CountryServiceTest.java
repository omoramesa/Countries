package com.test.countries.Services;

import com.test.countries.models.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class CountryServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private LogService logService;

    private CountryService countryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        countryService = new CountryService();
        countryService.setLogService(logService);
        countryService.setApiUrlAll("https://api.example.com/countries/all");
        countryService.setApiUrl("https://api.example.com/countries");
    }

    @Test
    public void testGetCountries() {
        String expectedResponse = "Country1, Country2, Country3";

        Mockito.when(restTemplate.getForObject(Mockito.eq("https://api.example.com/countries/all"), Mockito.eq(String.class)))
                .thenReturn(expectedResponse);

        String actualResponse = countryService.getCountries();

        Assertions.assertEquals(expectedResponse, actualResponse);
        Mockito.verify(logService).saveLog("todos los paises", "https://api.example.com/countries/all");
    }

    @Test
    public void testGetCountryByName() {
        String name = "Country1";
        String url = "https://api.example.com/countries/Country1?fullText=true";

        Country expectedCountry = new Country();

        expectedCountry.setArea(1000);
        expectedCountry.setPopulation(1000000);

        ResponseEntity<Country[]> responseEntity = new ResponseEntity<>(new Country[]{expectedCountry}, HttpStatus.OK);

        Mockito.when(restTemplate.exchange(Mockito.eq(url), Mockito.eq(HttpMethod.GET), Mockito.isNull(), Mockito.eq(Country[].class)))
                .thenReturn(responseEntity);

        Object actualCountry = countryService.getCountryByName(name);

        Assertions.assertEquals(expectedCountry, actualCountry);
        Mockito.verify(logService).saveLog(name, url);
    }

    @Test
    public void testGetCountryByName_NotFound() {
        String name = "NonExistentCountry";
        String url = "https://api.example.com/countries/NonExistentCountry?fullText=true";

        Mockito.when(restTemplate.exchange(Mockito.eq(url), Mockito.eq(HttpMethod.GET), Mockito.isNull(), Mockito.eq(Country[].class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        Object actualCountry = countryService.getCountryByName(name);

        Assertions.assertNull(actualCountry);
        Mockito.verify(logService).saveLog(name, url);
    }

    @Test
    public void testGetCountryByName_Exception() {
        String name = "Country1";
        String url = "https://api.example.com/countries/Country1?fullText=true";

        Mockito.when(restTemplate.exchange(Mockito.eq(url), Mockito.eq(HttpMethod.GET), Mockito.isNull(), Mockito.eq(Country[].class)))
                .thenThrow(new RuntimeException("API connection error"));

        Object actualCountry = countryService.getCountryByName(name);

        Assertions.assertNull(actualCountry);
        Mockito.verify(logService).saveLog(name, url);
    }
}