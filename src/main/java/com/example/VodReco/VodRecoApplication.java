package com.example.VodReco;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication // 이 어노테이션 안에 @ComponentScan 들어있음 = 하위 모두 ComponentScan의 대상
//@EnableMongoRepositories(basePackages = "com.example.VodReco.mongoRepository")
@EnableDynamoDBRepositories(basePackages = "com.example.VodReco.dynamoRepository") // DynamoDB Repository 패키지 위치 등록
@EnableJpaRepositories(basePackages = "com.example.VodReco.repository") // JpaRepository 패키지 위치 등록
public class VodRecoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VodRecoApplication.class, args);
	}

}
