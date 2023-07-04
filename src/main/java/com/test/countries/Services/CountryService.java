    package com.test.countries.Services;

    import com.test.countries.models.Country;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.http.HttpMethod;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Service;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.client.HttpClientErrorException;
    import org.springframework.web.client.RestTemplate;
    import org.springframework.web.util.UriComponentsBuilder;

    @Service
    public class CountryService {
        //Se realiza Depencency Injection
        @Autowired
        private  LogService logService;
        @Value("${api.url.all}")
        private String apiUrlAll;
        @Value("${api.url}")
        private String apiUrl;

        public CountryService() {
        }

        public LogService getLogService() {
            return logService;
        }

        public void setLogService(LogService logService) {
            this.logService = logService;
        }

        public String getApiUrlAll() {
            return apiUrlAll;
        }

        public void setApiUrlAll(String apiUrlAll) {
            this.apiUrlAll = apiUrlAll;
        }

        public String getApiUrl() {
            return apiUrl;
        }

        public void setApiUrl(String apiUrl) {
            this.apiUrl = apiUrl;
        }

        public String getCountries(){
            RestTemplate restTemplate = new RestTemplate();
            logService.saveLog("todos los paises", apiUrlAll);
            String response = restTemplate.getForObject(apiUrlAll, String.class);
            return response;
        }

        public Object getCountryByName(@PathVariable String name){
            RestTemplate restTemplate = new RestTemplate();
            Country[] countries = new Country[1];
            Country countriesResponse= new Country();
                    String url = UriComponentsBuilder.fromUriString(apiUrl)
                    .pathSegment(name)
                    .queryParam("fullText", "true")
                    .toUriString();

            try {
                logService.saveLog(name, url);
                //Country[] countries = restTemplate.getForObject(url, Country[].class);

                ResponseEntity<Country[]> response = restTemplate.exchange(url, HttpMethod.GET, null, Country[].class);

                if (response.getStatusCode() == HttpStatus.OK) {
                    countries = response.getBody();
                    if (countries != null && countries.length > 0) {
                        countriesResponse =countries[0];
                    }
                }
            } catch (HttpClientErrorException.NotFound ex) {
              ex.printStackTrace();
              countriesResponse = null;
            } catch (Exception e){
                e.printStackTrace();
                countriesResponse = null;
            }
            return countriesResponse;

        }
    }
