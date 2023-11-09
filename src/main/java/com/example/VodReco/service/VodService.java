package com.example.VodReco.service;

import com.example.VodReco.dto.VodDto;

import java.util.List;

public interface VodService {

    //    VodDto VodtoVodDto(Vod vod);
    List<VodDto> getAllVods();

    List<String> getAllPosterUrls();


    // vcode로 Vod 조회
    //Vod 엔티티 클래스 안에 toVodDto 메서드 있는데 굳이 VodtoVodDto 클래스 분리한 이유
    // : toVodDto 쓰려면 Vod vod라는 빈 객체 생성한 뒤 vod 인스턴스 안에 있는 toVodDto 메서드 호출해서 써야 함
    // 이게 번거로움?(?) + Vod클래스 안에 있는 메서드를 사용하는 것이 목적인데 vod인스턴스를 생성하는 것은 목적에 맞지 않는단 판단
    // 그렇다고 static으로 선언하면 그때부터 인스턴스에서 사용 불가능해짐 = vod.toVodDto 사용 불가
    VodDto getVodByContentId(String contentId);
}
