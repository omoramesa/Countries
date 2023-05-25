package com.test.countries.controllers;

import com.test.countries.Services.CountryService;
import com.test.countries.exception.CountryNotFoundException;
import com.test.countries.exception.ExceptionResponse;
import com.test.countries.models.Country;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CountryControllerTest {

    @Mock
    private CountryService countryService;

    @InjectMocks
    private CountryController countryController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCountries() {
        // Simular el comportamiento del servicio
        when(countryService.getCountries()).thenReturn("Lista de países");

        // Llamar al método del controlador
        ResponseEntity<?> responseEntity = countryController.getCountries();

        // Verificar el resultado esperado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Lista de países", responseEntity.getBody());

        // Se imprimen los resultados
        System.out.println("Resultado esperado (HttpStatus): " + HttpStatus.OK);
        System.out.println("Resultado obtenido (HttpStatus): " + responseEntity.getStatusCode());
        System.out.println("Resultado esperado (Body): " + "Lista de países");
        System.out.println("Resultado obtenido (Body): " + responseEntity.getBody());

        // Verificar si el método del servicio fue llamado una vez
        verify(countryService, times(1)).getCountries();
    }

    @Test
    public void testGetCountryByName_ExistingCountry() throws CountryNotFoundException {
        // Crear un objeto de país simulado
        Country country = new Country();
        country.setArea(100);
        country.setPopulation(1000);

        // Simular el comportamiento del servicio
        when(countryService.getCountryByName("Argentina")).thenReturn(country);

        // Llamar al método del controlador
        ResponseEntity<?> responseEntity = countryController.getCountryByName("Argentina");

        // Verificar el resultado esperado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(country.getArea(), ((Country) responseEntity.getBody()).getArea());
        assertEquals(country.getPopulation(), ((Country) responseEntity.getBody()).getPopulation());

        // Imprimir los resultados
        System.out.println("Resultado esperado (HttpStatus): " + HttpStatus.OK);
        System.out.println("Resultado obtenido (HttpStatus): " + responseEntity.getStatusCode());
        System.out.println("Resultado esperado (Area): " + country.getArea());
        System.out.println("Resultado obtenido (Area): " + ((Country) responseEntity.getBody()).getArea());
        System.out.println("Resultado esperado (Población): " + country.getPopulation());
        System.out.println("Resultado obtenido (Población): " + ((Country) responseEntity.getBody()).getPopulation());

        // Verificar si el método del servicio fue llamado una vez
        verify(countryService, times(1)).getCountryByName("Argentina");
    }

    @Test
    public void testGetCountryByName_NonExistingCountry() throws CountryNotFoundException {
        // Simular una excepción lanzada por el servicio
        when(countryService.getCountryByName("Argentina")).thenThrow(new CountryNotFoundException("País no encontrado"));

        // Llamar al método del controlador
        ResponseEntity<?> responseEntity = countryController.getCountryByName("Argentina");

        // Verificar el resultado esperado
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof ExceptionResponse); // Verificar si el cuerpo es una instancia de ExceptionResponse

        ExceptionResponse exceptionResponse = (ExceptionResponse) responseEntity.getBody();
        assertEquals("País no encontrado", exceptionResponse.getMessage());

        // Imprimir los resultados
        System.out.println("Resultado esperado (HttpStatus): " + HttpStatus.NOT_FOUND);
        System.out.println("Resultado obtenido (HttpStatus): " + responseEntity.getStatusCode());
        System.out.println("Resultado esperado (Body): " + "País no encontrado");
        System.out.println("Resultado obtenido (Body): " + exceptionResponse.getMessage());

        // Verificar si el método del servicio fue llamado una vez
        verify(countryService, times(1)).getCountryByName("Argentina");
    }
}