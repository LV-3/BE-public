package com.example.VodReco.service;

import com.example.VodReco.dto.model.FromModelDto;
import jakarta.persistence.criteria.From;
import lombok.Getter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Getter
@Service
public class KafkaConsumerService {
    private FromModelDto processedData;
    @KafkaListener(topics = "consuming-test", groupId = "my-group", containerFactory =
            "kafkaListener")
    public void consume(FromModelDto fromModelDto) {
        System.out.println("확인 = " + fromModelDto);
        System.out.println("descriptionData = " + fromModelDto.getDescription_data());
        System.out.println("genreData = " + fromModelDto.getGenre_data());
        System.out.println("personalData = " + fromModelDto.getPersonal_data());

        this.processedData = processData(fromModelDto);

    }

    private FromModelDto processData(FromModelDto fromModelDto) {
        return FromModelDto.builder().description_data(fromModelDto.getDescription_data()).genre_data(fromModelDto.getGenre_data()).personal_data(fromModelDto.getPersonal_data()).build();
    }
}
