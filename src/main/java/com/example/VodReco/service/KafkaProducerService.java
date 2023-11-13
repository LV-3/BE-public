package com.example.VodReco.service;

import com.example.VodReco.dto.model.ToModelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private static final String TOPIC = "exam-topic";
    private final KafkaTemplate<String, ToModelDto> kafkaTemplate;
    public void sendMessage(ToModelDto toModelDto) {
        System.out.println("modelName = " + toModelDto.getModelName());
        kafkaTemplate.send(TOPIC, toModelDto);
    }


}
