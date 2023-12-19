//package com.example.VodReco.service.logging;
//
//import com.example.VodReco.domain.LogEntity;
//import com.example.VodReco.mongoRepository.LoggingRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class LoggingServiceImpl implements LoggingService {
//    private final LoggingRepository loggingRepository;
//
//    @Autowired
//    public LoggingServiceImpl(LoggingRepository loggingRepository){
//        this.loggingRepository = loggingRepository;
//    }
//    @Transactional
//    @Override
//    public void saveLog(LogEntity logEntity) {
//        loggingRepository.save(logEntity);
//    }
//
//    @Override
//    public void saveLogConsole(LogEntity log) {
//        System.out.println("Logging ipAddress: " + log.getIpAddress());
//        System.out.println("Logging RequestURL: " + log.getRequestURL());
//        System.out.println("Logging Action: " + log.getAction());
//        System.out.println("Logging Details: " + log.getDetails());
//        System.out.println("Logging Timestamp: " + log.getTimestamp());
//        System.out.println("Logging isSuccess: " + log.isSuccess());
//    }
//
//}
