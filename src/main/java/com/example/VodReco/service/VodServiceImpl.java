//package com.example.VodReco.service;
//
//import com.example.VodReco.dto.VodDto;
//import com.example.VodReco.dynamoRepository.VodRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class VodServiceImpl implements VodService {
//    private final VodRepository vodRepository;
//
//    @Autowired
//    public VodServiceImpl(VodRepository vodRepository) {
//        this.vodRepository = vodRepository;
//    }
//
//
//    //전체 VOD 조회
//
//    //테스트 코드 작성 포기함(231101)
//    @Override
//    public List<VodDto> getAllVods() {
//        return vodRepository.findAll().stream(`)
//                .map(VodtoVodDto::vodtoVodDto)
//                .collect(Collectors.toList());
//    }
//
//
//    //모든 posterUrl 조회
//    @Override
//    public List<String> getAllPosterUrls() {
//        List<String> posterUrls = new ArrayList<>();
//        for (VodDto vodDto : getAllVods()) {
//            String posterUrl = vodDto.getPosterurl();
//            posterUrls.add(posterUrl);
//        }
//        return posterUrls;
//    }
//
//
//}
