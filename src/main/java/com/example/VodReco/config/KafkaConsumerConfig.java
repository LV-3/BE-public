package com.example.VodReco.config;

import com.example.VodReco.dto.CQRS.UpdatedWishDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;
@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String servers;
    @Bean
    public ConsumerFactory<String, UpdatedWishDto> consumerFactoryForWish() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(UpdatedWishDto.class));
//        return new DefaultKafkaConsumerFactory<>(config);
    }
    @Bean
    //containerFactor 이름을 kafkaListener에서 kafkaListenerForWish로 변경 -> KafkaConsumerService에서 @KafkaListener 어노테이션에서 containerFactory도 바꿔야 함!
    public ConcurrentKafkaListenerContainerFactory<String, UpdatedWishDto>
    kafkaListenerForWish() {
        ConcurrentKafkaListenerContainerFactory<String, UpdatedWishDto> factory = new
                ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryForWish());
        return factory;
    }

//    @Bean
//    public ConsumerFactory<String, UpdatedRatingDto> consumerFactoryForRating() {
//        Map<String, Object> config = new HashMap<>();
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
//        config.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group");
////        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new
////                JsonDeserializer<>(StringDto.class));
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//
//        return new DefaultKafkaConsumerFactory<>(config);
//    }
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, UpdatedRatingDto>
//    kafkaListenerForRating() {
//        ConcurrentKafkaListenerContainerFactory<String, UpdatedRatingDto> factory = new
//                ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactoryForRating());
//        return factory;
//    }
}