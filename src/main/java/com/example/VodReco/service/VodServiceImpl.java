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
    private final VodtoVodDto vodtoVodDto;


    @Autowired
    public VodServiceImpl(VodRepository vodRepository, VodtoVodDto vodtoVodDto) {
        this.vodRepository = vodRepository;
        this.vodtoVodDto = vodtoVodDto;
    }




    //전체 VOD 조회
    @Override
    public List<VodDto> getAllVods(){
        return vodRepository.findAll().stream()
                .map(VodtoVodDto::vodtoVodDto)
                .collect(Collectors.toList());

    }


    //모든 posterU
    // rl 조회
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
    public VodDto getVod(String vcode) {
        return vodtoVodDto.vodtoVodDto(vodRepository.findByVcode(vcode));
    }


}
