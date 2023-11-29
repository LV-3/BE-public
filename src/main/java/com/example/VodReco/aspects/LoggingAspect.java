package com.example.VodReco.aspects;

import com.example.VodReco.domain.LogEntity;
import com.example.VodReco.service.logging.LoggingService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private final LoggingService loggingService;

    @Autowired
    public LoggingAspect(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @Before("execution(* com.example.VodReco..*Controller.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ipAddress = request.getRemoteAddr();
        String requestURL = request.getRequestURL().toString();
        String methodName = joinPoint.getSignature().toShortString();
        String args = Arrays.toString(joinPoint.getArgs());
        LogEntity log = new LogEntity(ipAddress, requestURL, methodName, args, false, LocalDateTime.now());

        loggingService.saveLog(log);
        loggingService.saveLogConsole(log);
    }

    @AfterReturning(pointcut = "execution(* com.example.VodReco..*Controller.*(..))",
            returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ipAddress = request.getRemoteAddr();
        String requestURL = request.getRequestURL().toString();
        String methodName = joinPoint.getSignature().toShortString();
        String args = Arrays.toString(joinPoint.getArgs());
        boolean isSuccess = isSuccess(result);
        LogEntity log = new LogEntity(ipAddress, requestURL, methodName, args, isSuccess, LocalDateTime.now());
        loggingService.saveLog(log);
        loggingService.saveLogConsole(log);
    }

    private boolean isSuccess(Object result) {
        if (result instanceof ResponseEntity) {
            ResponseEntity<?> responseEntity = (ResponseEntity<?>) result;
            return responseEntity.getStatusCode().is2xxSuccessful();
        } else {
            //필요하면 구현하자...
            return false;
        }
    }
}

