package com.example.VodReco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication // 이 어노테이션 안에 @ComponentScan 들어있음 = 하위 모두 ComponentScan의 대상
@EnableMongoRepositories(basePackages = "com.example.VodReco.mongoRepository")
public class VodRecoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VodRecoApplication.class, args);
	}

}
