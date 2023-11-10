//package com.example.VodReco.service;
//
//import com.example.VodReco.dto.ChatMessage;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//@Service
//public class KafkaConsumerService {
//    @KafkaListener(topics = "testTopic", groupId = "testgroup", containerFactory =
//            "kafkaListener")
//    public void consume(ChatMessage message){
//        System.out.println("name = " + message.getSender());
//        System.out.println("consume message = " + message.getContext());
//    }
//}
