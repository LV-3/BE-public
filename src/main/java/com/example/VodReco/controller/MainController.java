package com.example.VodReco.controller;

import com.example.VodReco.dto.model.DescriptionResponseDto;
import com.example.VodReco.service.VodServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {  //메인페이지

//    private final VodServiceImpl vodServiceImpl;
//    private final DescriptionResponseDto descriptionResponseDto; // 이걸 Bean에 등록해서 써야 하나?;;(231102)
//
//    public MainController(VodServiceImpl vodServiceImpl) {
//        this.vodServiceImpl = vodServiceImpl;
//    }


//1
//    # 줄거리 기반 연관 추천 모델에 필요한 데이터
//# 컨텐츠 고유 id, 컨텐츠 줄거리
//    data = {"content_id":"value1","description":"value2"}
//
//
//# 장르 기반 연관 추천 모델에 필요한 데이터
//# 컨텐츠 고유 id, 컨텐츠 장르들
//    data = {"content_id":"value1","genre":"value2" (list)}


//    @PostMapping("/main")
//    //수정: DescriptionRequestDto의 vcode는 List<String>으로 들어올 것. 이걸 .Size()해서 크기 알아낸 뒤 for문 돌려서 하나씪 꺼내든지
    //아니면 뭔가 함수를 써서 요소를 하나하나 꺼내서~ getVod해서 vodDto 받은 다음 각기 getDescription해서 꺼내서 new DescriptionResponseDto에 담아 리턴
//    public DescriptionResponseDto descriptionReco(@RequestBody DescriptionRequestDto descriptionRequestDto) {
//        VodDto vod = vodServiceImpl.getVod(descriptionRequestDto.getVcode());
//    }


}
