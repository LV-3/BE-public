package com.example.VodReco.service;

import com.example.VodReco.dto.ChatMessage;
import com.example.VodReco.dto.model.FromModelDto;
import jakarta.persistence.criteria.From;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class KafkaConsumerService {
    private FromModelDto processedData;
    @KafkaListener(topics = "exam-topic", groupId = "adamsoft", containerFactory =
            "kafkaListener")
    public void consume(FromModelDto fromModelDto) {
        System.out.println("확인 = " + fromModelDto);
        System.out.println("descriptionData = " + fromModelDto.getDescription_data());
        System.out.println("genreData = " + fromModelDto.getGenre_data());
        System.out.println("personalData = " + fromModelDto.getPersonal_data());

        this.processedData = processData(fromModelDto);

    }
    public FromModelDto getProcessedData(){
        return processedData;
    }

    private FromModelDto processData(FromModelDto fromModelDto) {
        return FromModelDto.builder().description_data(fromModelDto.getDescription_data()).genre_data(fromModelDto.getGenre_data()).personal_data(fromModelDto.getPersonal_data()).build();
    }
}
