package com.example.VodReco.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
//@SpringBootTest
class VodTest {
    Vod vod;

    @BeforeEach
    public void beforeEach(){

    }
    @AfterEach
    public void afterEach(){
        Vod vod = new Vod();
    }

    @Test
    public void actor_한명도_가능하냐(){
        //given
        Vod vod = Vod.builder().actor("출연자 한명인디ㅋㅋ").mood("사랑스러운,유쾌한,집가고싶게하는").build();
        //when
        List<String> actorList = vod.actorToList(vod.getActor());
        List<String> moodList = vod.moodToList(vod.getMood());
        System.out.println("가능? = " + actorList + moodList);
        //then
        assertEquals(actorList.size(), 1);
        assertEquals(moodList.size(), 3);
    }
}