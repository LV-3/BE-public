package com.example.VodReco.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimeUtil {
    public String getTimeGroup(LocalDateTime now) {
        int hour = now.getHour();
        if (hour >= 18 || hour < 6) {
            return "night";
        } else if (hour >= 6 && hour < 12) {
            return "am";
        } else if (hour >= 12 && hour < 18) {
            return "pm";
        } else {
            return "dawn";
        }
    }
}

