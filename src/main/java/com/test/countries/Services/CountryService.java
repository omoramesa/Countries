package com.test.countries.Services;

import com.test.countries.models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryService {
    @Autowired
    private  LogService logService;
    @Value("${api.url.all}")
    private String apiUrlAll;
    @Value("${api.url}")
    private String apiUrl;

    public String getCountries(){
        RestTemplate restTemplate = new RestTemplate();
        logService.saveLog("todos los paises", apiUrlAll);
        String response = restTemplate.getForObject(apiUrlAll, String.class);
        return response;
    }

    public Country getCountryByName(@PathVariable String name){
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + name + "?fullText=true";

        try {
            logService.saveLog(name, url);
            Country[] countries = restTemplate.getForObject(url, Country[].class);

            if (countries != null && countries.length > 0) {
                return countries[0];
            }
        }catch (HttpClientErrorException.NotFound ex){
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El país '" + name + "' no fue encontrado o no existe.");
        }
        return null;
    }
}
