package com.example.VodReco.service;

import com.example.VodReco.dto.VodDto;
import com.example.VodReco.repository.VodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VodServiceImpl implements VodService{
    private final VodRepository vodRepository;

    @Autowired
    public VodServiceImpl(VodRepository vodRepository) {
        this.vodRepository = vodRepository;
    }


    //전체 VOD 조회

    //테스트 코드 작성 포기함(231101)
    @Override
    public List<VodDto> getAllVods(){
        return vodRepository.findAll().stream()
                .map(VodtoVodDto::vodtoVodDto)
                .collect(Collectors.toList());

    }


    //모든 posterUrl 조회
    @Override
    public List<String> getAllPosterUrls(){
        List<String> posterUrls = new ArrayList<>();
        for (VodDto vodDto: getAllVods()){
            String posterUrl = vodDto.getPosterurl();
            posterUrls.add(posterUrl);
        }
        return posterUrls;
    }

    // vcode로 Vod 조회
    @Override
    //Vod 엔티티 클래스 안에 toVodDto 메서드 있는데 굳이 VodtoVodDto 클래스 분리한 이유
    // : toVodDto 쓰려면 Vod vod라는 빈 객체 생성한 뒤 vod 인스턴스 안에 있는 toVodDto 메서드 호출해서 써야 함
    // 이게 번거로움?(?) + Vod클래스 안에 있는 메서드를 사용하는 것이 목적인데 vod인스턴스를 생성하는 것은 목적에 맞지 않는단 판단
    // 그렇다고 static으로 선언하면 그때부터 인스턴스에서 사용 불가능해짐 = vod.toVodDto 사용 불가
    public VodDto getVodByContentId(String contentId) {
        return VodtoVodDto.vodtoVodDto(vodRepository.findByContentId(contentId));

        //VodtoVodDto 사용하지 않는 경우의 리턴 코드

//        Vod vod = new Vod();
//        return vod.toVodDto(vodRepository.findByVcode(vcode));

    }
}
