package com.example.VodReco.dto.log;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class loggingDto {
    private String ipAddress;
    private String requestURL;
    private String action;
    private String details;
    private boolean isSuccess;
    private LocalDateTime timestamp;

    @Builder
    public loggingDto(String ipAddress, String requestURL, String action, String details, boolean isSuccess, LocalDateTime timestamp) {
        this.ipAddress = ipAddress;
        this.requestURL = requestURL;
        this.action = action;
        this.details = details;
        this.isSuccess = isSuccess;
        this.timestamp = timestamp;
    }
}
