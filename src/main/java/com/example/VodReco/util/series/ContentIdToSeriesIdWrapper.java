//package com.example.VodReco.util.series;
//
//import com.example.VodReco.mongoRepository.VodRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class ContentIdToSeriesIdWrapper {
//    private final VodRepository vodRepository;
//
//    //content_id 받아서 바로 seriesId 리턴하는 메서드
//    public String getSeriesId(String contentId) {
//        return this.getSeriesIdFromContentId(contentId);
//    }
//    private String getSeriesIdFromContentId(String contentId) {
//        return vodRepository.findByContentId(contentId).getSeriesId();
//    }
//}
