package com.test.countries.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountryTest {


    @Test
    public void testGetArea() {
        long expectedArea = 100_000;
        Country country = new Country();
        country.setArea(expectedArea);

        long actualArea = country.getArea();

        Assertions.assertEquals(expectedArea, actualArea);
    }

    @Test
    public void testGetPopulation() {
        long expectedPopulation = 1_000_000;
        Country country = new Country();
        country.setPopulation(expectedPopulation);

        long actualPopulation = country.getPopulation();

        Assertions.assertEquals(expectedPopulation, actualPopulation);
    }

    @Test
    public void testSetArea() {
        long expectedArea = 100_000;
        Country country = new Country();
        country.setArea(expectedArea);

        Assertions.assertEquals(expectedArea, country.getArea());
    }

    @Test
    public void testSetPopulation() {
        long expectedPopulation = 1_000_000;
        Country country = new Country();
        country.setPopulation(expectedPopulation);

        Assertions.assertEquals(expectedPopulation, country.getPopulation());
    }
}