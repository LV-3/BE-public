//package com.example.VodReco.mongoRepository;
//
//import com.example.VodReco.domain.Vod;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class VodRepositoryTest {
//    @Autowired
//    private VodRepository vodRepository;
//
//    @BeforeEach
//    public void settingVodRepository() {
//        Vod savedVod =  vodRepository.save(Vod.builder().contentId("22222222").description("공유경바부래요꺄갹").build());
//    }
//
//    @Test
//    public void testNewMongoDBConnection() {
//        Vod foundVod = vodRepository.findByContentId("22222222");
//        assertEquals("공유경바부래요꺄갹", foundVod.getDescription());
//    }
//    @AfterEach
//    public void afterVodRepository() {
//        vodRepository.delete(Vod.builder().contentId("22222222").description("공유경바부래요꺄갹").build());
//    }
//
//}