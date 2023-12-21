package com.example.VodReco.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimeUtil {
    public String getTimeGroup(LocalDateTime now) {
        int hour = now.getHour();
        //0-6(오후 3시부터 저녁 9시), 6-12(저녁 9시부터 새벽 3시), 12-18(새벽 3시부터 오전 9시), 18-24(오전9시-오후 3시)

        if(hour >= 15 && hour < 21){
            return "dawn";
        } else if (hour >= 21 && hour < 3) {
            return "am";
        } else if (hour >= 9 && hour < 15) {
            return "pm";
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

