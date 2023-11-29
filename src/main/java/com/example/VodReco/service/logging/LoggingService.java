package com.example.VodReco.service.logging;

import com.example.VodReco.domain.LogEntity;
import com.example.VodReco.repository.LoggingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class LoggingService {
    private final LoggingRepository loggingRepository;

    @Autowired
    public LoggingService(LoggingRepository loggingRepository){
        this.loggingRepository = loggingRepository;
    }
    @Transactional
    public void saveLog(LogEntity logEntity) {
        loggingRepository.save(logEntity);
    }

    public void saveLogConsole(LogEntity log) {
        System.out.println("Logging ipAddress: " + log.getIpAddress());
        System.out.println("Logging RequestURL: " + log.getRequestURL());
        System.out.println("Logging Action: " + log.getAction());
        System.out.println("Logging Details: " + log.getDetails());
        System.out.println("Logging Timestamp: " + log.getTimestamp());
        System.out.println("Logging isSuccess: " + log.isSuccess());
    }

}
