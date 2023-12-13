package com.example.VodReco.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.ServiceUnavailableException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //백엔드 서비스가 이용 불가능한 경우
    //HTTP 상태 코드 503(Service Unavailable)
    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<Object> handleServiceUnavailableException(ServiceUnavailableException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
    }

    //코드 상에서 발생 -> 예상하지 못한 모든 예외
    //HTTP 상태 코드 500(Internal Server Error)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

}
