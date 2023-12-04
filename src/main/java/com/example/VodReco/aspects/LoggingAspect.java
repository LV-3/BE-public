package com.example.VodReco.aspects;

import com.example.VodReco.domain.LogEntity;
import com.example.VodReco.service.logging.LoggingServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private final LoggingServiceImpl loggingService;

    @Autowired
    public LoggingAspect(LoggingServiceImpl loggingService) {
        this.loggingService = loggingService;
    }

    //메서드 시간측정(231203)
    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);


    @Before("execution(* com.example.VodReco..*Controller.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ipAddress = request.getRemoteAddr();
        String requestURL = request.getRequestURL().toString();
        String methodName = joinPoint.getSignature().toShortString();
        String args = Arrays.toString(joinPoint.getArgs());
        LocalDateTime timestamp = LocalDateTime.now();
        // 시간대 분류
        String timeCategory = categorizeTime(timestamp.getHour());
        LogEntity log = new LogEntity("Before", ipAddress, requestURL, methodName, args, false, LocalDateTime.now(), timeCategory);

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
        LocalDateTime timestamp = LocalDateTime.now();
        // 시간대 분류
        String timeCategory = categorizeTime(timestamp.getHour());
        LogEntity log = new LogEntity("After", ipAddress, requestURL, methodName, args, isSuccess, LocalDateTime.now(),timeCategory);
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

    private String categorizeTime(int hour) {
        if (hour >= 0 && hour < 6) {
            return "dawn";
        } else if (hour >= 6 && hour < 12) {
            return "am";
        } else if (hour >= 12 && hour < 18) {
            return "pm";
        } else {
            return "night";
        }
    }


    @Around("@annotation(LogExecutionTime)")
    //LogExecutionTime 어노테이션 파일과 같은 클래스에 있어야 함.
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        //타겟 메서드 실행
        Object proceed = joinPoint.proceed();

        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());

        return proceed;

    }
}

