package com.example.VodReco.dto.log;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LoggingDto {
    private String divide;
    private String ipAddress;
    private String requestURL;
    private String action;
    private String details;
    private boolean isSuccess;
    private LocalDateTime timestamp;

    @Builder
    public LoggingDto(String divide, String ipAddress, String requestURL, String action, String details, boolean isSuccess, LocalDateTime timestamp) {
        this.divide = divide;
        this.ipAddress = ipAddress;
        this.requestURL = requestURL;
        this.action = action;
        this.details = details;
        this.isSuccess = isSuccess;
        this.timestamp = timestamp;
    }
}
