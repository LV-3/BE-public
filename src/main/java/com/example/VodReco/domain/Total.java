//package com.example.VodReco.domain;
//
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Document
//@Getter
//@RequiredArgsConstructor
//
////전면 수정 필요
////태이블에는 subsr, content_id밖에 없고 mood와 description은 vod테이블에서 조회해서 사용해야 함(231116)
//public class Total {
//    private String subsr;
//    private String content_id;
//    private List<String> mood; //확인 필요(231116)
//    private String description;
//
////    public List<String> moodToList(String mood) {
////        String[] splitedMood = mood.split(",");
////        return Arrays.stream(splitedMood).toList();
////    }
//}
