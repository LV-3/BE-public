//package com.example.VodReco.service;
//
//import com.example.VodReco.domain.Vod;
//import com.example.VodReco.dto.VodDto;
//import com.example.VodReco.repository.VodRepository;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class VodServiceTest {
//
//    @Mock // @Mock과 @MockBean 차이?(231101)
//    private VodRepository vodRepository;
//
//
//    @Test
//    public void vcode로_Vod_조회하기() {
//        String vcode = "20200622";
//        VodRepository vodRepository = Mockito.mock(VodRepository.class);
//        Mockito.when(vodRepository.findByVcode("20200622"))
//                .thenReturn(new Vod("20200622"));
//        VodServiceImpl vodServiceImpl = new VodServiceImpl(vodRepository); // Replace with your service constructor
////    }
//
//            //when11
//            VodDto testVodDto = vodServiceImpl.getVod("20200622");
//            //then
//
//        }
//
//    }