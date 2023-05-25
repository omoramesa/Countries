package com.test.countries.controllers;

import com.test.countries.models.Country;
import com.test.countries.Services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.test.countries.exception.CountryNotFoundException;
import com.test.countries.exception.ExceptionResponse;



@RestController
@RequestMapping("api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/all")
    public ResponseEntity<?> getCountries(){
        String response = countryService.getCountries();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<?> getCountryByName(@PathVariable String name) {
        try{
        Country country = (Country) countryService.getCountryByName(name);

            if (country != null) {
                Country countryInfo = new Country();
                countryInfo.setArea(country.getArea());
                countryInfo.setPopulation(country.getPopulation());
                return ResponseEntity.ok(countryInfo);
            }else{
                String msg = "El país '" + name + "' no fue encontrado";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
            }

        }catch (CountryNotFoundException ex) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
        }


    }
}
