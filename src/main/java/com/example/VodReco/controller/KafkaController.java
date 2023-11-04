//package com.example.VodReco.controller;
//
//import com.example.VodReco.service.KafkaProducer;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping(value = "/kafka")
//@Slf4j
//@RequiredArgsConstructor
//public class KafkaController {
//    private final KafkaProducer producer;
//    @PostMapping
//    @ResponseBody
//    public String sendMessage(@RequestParam String message) {
//        log.info("message : {}", message);
//        this.producer.sendMessage(message);
//        return "success";
//    }
//}