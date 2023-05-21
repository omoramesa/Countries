package com.technical_test.countries;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/countries")
    public class CountryController {


        @GetMapping("/hola")
        public String saludar(){
            return "Hola mundo";
        }
    /**
        @GetMapping
        public List<Country> getAllCountries() {
            return countryService.getAllCountries();
        }

    */
}
