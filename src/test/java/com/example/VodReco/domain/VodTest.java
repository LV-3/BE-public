//package com.example.VodReco.domain;
//
//import com.example.VodReco.dto.VodDto;
//import com.example.VodReco.util.ListToStringWrapper;
//import com.example.VodReco.util.StringToListWrapper;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
////로그는 NoSQL에 넣을 것
////데이터 엔지니어가 원래는 로그 모아서 데이터 레이크에 넣어주거나 하는 일을 해야 함. 분석가가 쓸 수 있게 만들어 주는 게 목적.
////요즘은 ETL이 아니ㅏㄹ ELT. 일단 다 저장해라. S3에 csv파일 올리는 정도. spring batch로 1달에 한 번씩/
//
//@RunWith(SpringRunner.class)
////@SpringBootTest
//class VodTest {
//    Vod vod;
//    StringToListWrapper stringToListWrapper;
//    ListToStringWrapper listToStringWrapper;
//
//    @BeforeEach
//    public void beforeEach(){
//
//    }
//    @AfterEach
//    public void afterEach(){
//        Vod vod = new Vod();
//    }
//
//    @Test
//    public void 배우_한명도_가능할까(){
//        //given
//        Vod vod = Vod.builder().actors("공효진").build();
//        List<String> a = new ArrayList<>();
//        a.add("조인성");
//        VodDto vodDto = VodDto.builder().actors(a).build();
//        //when
//        List<String> actorsList = stringToListWrapper.stringToList(vod.getActors());
//        String actorsString = listToStringWrapper.listToString(vodDto.getActors());
//        System.out.println("배우 1명인데 String -> List 가능? = " + actorsList);
//        System.out.println("배우 1명인데 List -> String 가능? = " + actorsString);
//        //then
//        assertEquals(actorsList.size(), 1);
//        assertEquals(actorsString.length(), 6);
//    }
//}