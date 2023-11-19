package com.example.VodReco.service;

import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Getter
@Service
public class KafkaConsumerService {
    private String messageFromModel;
    @KafkaListener(topics = "consuming2", groupId = "my-group", containerFactory =
            "kafkaListener")
    public void consume(String message) throws ParseException {
        System.out.println("메시지 들어왔니 = " + message);

        this.messageFromModel = message;

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(message);

        JSONArray descriptionArray = (JSONArray) jsonObject.get("description_data");
        JSONArray genreArray = (JSONArray) jsonObject.get("genre_data");
        JSONArray personalArray = (JSONArray) jsonObject.get("personal_data");
        System.out.println("소비한 description데이터 파싱 = " + descriptionArray);
        System.out.println("소비한 genre데이터 파싱 = " + genreArray);
        System.out.println("소비한 personal데이터 파싱 = " + personalArray);

    }
}
