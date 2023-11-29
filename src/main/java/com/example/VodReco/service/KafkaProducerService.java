
package com.example.VodReco.service;

import com.example.VodReco.dto.CQRS.UpdatedWishDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private static final String TOPIC = "lv3-topic";
    private final KafkaTemplate<String, UpdatedWishDto> kafkaTemplateForWish;
//    private final KafkaTemplate<String, ChatMessage> kafkaTemplateForRating;

    public void sendWish(UpdatedWishDto updatedWishDto) {
        System.out.println("wish 업데이트 uniqueId = " + updatedWishDto.getUpdateMyWishDto().getUniqueId());
        kafkaTemplateForWish.send(TOPIC, updatedWishDto);
    }
}