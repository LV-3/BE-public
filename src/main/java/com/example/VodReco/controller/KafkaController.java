package com.example.VodReco.controller;

import com.example.VodReco.dto.ChatMessage;
import com.example.VodReco.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

//
//import com.example.VodReco.dto.ChatMessage;
//import com.example.VodReco.service.KafkaProducerService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@Slf4j
//@RequiredArgsConstructor
//@RequestMapping(value = "/kafka")
//public class KafkaController {
//    private final KafkaProducerService producerService;
//    @PostMapping
//    @ResponseBody
//    public String sendMessage(@RequestParam ChatMessage chatMessage) {
//        System.out.println("chatmessage = " + chatMessage);
//        producerService.sendMessage(chatMessage);
//        return "success";
//    }
//}
@RestController
@RequestMapping(value = "/kafka")
@Slf4j
@RequiredArgsConstructor
public class KafkaController {
    private final KafkaProducerService producerService;
    @PostMapping
    @ResponseBody
    public String sendMessage(@RequestBody ChatMessage chatmessage) {
        System.out.println("chatmessage = " + chatmessage);
        producerService.sendMessage(chatmessage);
        return "success";
    }
}
