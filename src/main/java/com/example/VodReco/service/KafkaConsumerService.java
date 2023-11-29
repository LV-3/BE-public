package com.example.VodReco.service;

import com.example.VodReco.dto.CQRS.UpdatedWishDto;
import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Getter
@Service
public class KafkaConsumerService {
    private UpdatedWishDto updatedWish;
//    private UpdatedRatingDto updatedRating;
    @KafkaListener(topics = "lv3-topic", groupId = "my-group", containerFactory =
            "kafkaListenerForWish")
    public void consumeWish(UpdatedWishDto updatedWishDto) throws ParseException {
        System.out.println("업데이트 wish의 uniqueId = " + updatedWishDto);

        this.updatedWish = updatedWishDto;
//        this.updatedRating = updatedRatingDto;

        System.out.println("consumer 테스트 = " + updatedWishDto);

//        JSONParser jsonParser = new JSONParser();
//        JSONObject jsonObject = (JSONObject) jsonParser.parse(String.valueOf(updatedWishDto));
//        JSONArray descriptionArray = (JSONArray) jsonObject.get("description_data");

//        System.out.println("소비한 description데이터 파싱 = " + descriptionArray);

    }
}
