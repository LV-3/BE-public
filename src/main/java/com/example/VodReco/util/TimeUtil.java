package com.example.VodReco.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimeUtil {
    public String getTimeGroup(LocalDateTime now) {
        int hour = now.getHour();
        System.out.print(hour);
        if (hour >= 21 && hour < 3){
            return "am";
        } else if (hour >= 3 && hour < 9) {
            return "pm";
        } else if (hour >= 15 && hour < 21){
            return "dawn";
        } else {
            return "night";
        }

//        if (hour >= 18) {
//            return "night";
//        } else if (hour >= 6 && hour < 12) {
//            return "am";
//        } else if (hour >= 12 && hour < 18) {
//            return "pm";
//        } else {
//            return "dawn";
//        }
    }
}

