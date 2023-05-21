package com.test.countries.Services;

import com.test.countries.models.Log;
import com.test.countries.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogService {
    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository){
        this.logRepository = logRepository;
    }

    /**
     * Método que permite guardar un registro en base de datos cada vez que se accede al endpoint
     * de consulta de todos los paises del mundo o por nombre de pais
     * @param name
     * @param url
     */
    public void saveLog(String name, String url){
        LocalDateTime timestamp = LocalDateTime.now();
        Log log = new Log(name, url, timestamp);
        logRepository.save(log);
    }
}
