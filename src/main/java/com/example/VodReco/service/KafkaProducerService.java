package com.example.VodReco.service;

import com.example.VodReco.dto.model.EveryDescription;
import com.example.VodReco.dto.model.EveryGenre;
import com.example.VodReco.dto.model.ToModelDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.util.Json;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private static final String TOPIC = "producing-test";

    @Autowired
    private final KafkaTemplate<String, ToModelDto> kafkaTemplate;

    public void sendMessage(ToModelDto toModelDto) {
        kafkaTemplate.send(TOPIC, toModelDto);
    }
}
