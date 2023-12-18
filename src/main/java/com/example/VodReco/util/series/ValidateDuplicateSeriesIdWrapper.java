//package com.example.VodReco.util.series;
//
//import com.example.VodReco.domain.Vod;
//import com.example.VodReco.mongoRepository.VodRepository;
//import com.example.VodReco.util.Vod.VodtoVodDtoWrapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//
//
////이 클래스의 작성 목적: raw content_id 리스트를 파라미터로 받아서
////findByContentId로 vod 찾은 뒤 getIsSeries해서 if 1 && getSeriesId != null이면
////uniqueSeriesIdSet에 add한 뒤
////Set에서 loop 돌리기. 각 시리즈id를 findBy해서 다시 vod리스트 뽑은 다음 각 seriesId당 content_id 리스트 만들고
////오름차순 sorting해서 가장 작은 content_id를 빈 ArrayList에 add.
////처음에 시리즈물이 아니라 판명났으면 content_id 바로 집어넣기
////이 content_id 리스트를 List<String>으로 리턴하는 것까지 만들기.
//
////BasicInfoOfVodDto 만드는 건 따로 분리
//
//
////contentId 파라미터로 받아서 -> isSeries == 1 또는 seriesId != null(상황에 따라 다름)이면 seriesId 리턴하는 메서드 만들기
////TODO : if isSeries == 1 / if seriesId != null 조건문은 상황에 따라 알아서 걸고
////TODO : 그 이후부터 content_id로 seriesId 뽑는 부분을 메서드로 만들기
//
//@Component
//@RequiredArgsConstructor
//public class ValidateDuplicateSeriesIdWrapper {
//    private final VodtoVodDtoWrapper vodtoVodDtoWrapper;
//    private final VodRepository vodRepository;
//
//    public List<String> validateDuplicateSeriesId(List<String> contentIdsList) {
//        return makeSeriesIdUnique(contentIdsList);
//    }
//
//    //content_id 리스트 받아서 시리즈물 처리(시리즈물이라면 가장 작은 content_id 하나로 대체)된 새로운 content_id 리스트를 반환하는 메서드
//    private List<String> makeSeriesIdUnique(List<String> contentIdsList) {
//        List<String> contentIds = new ArrayList<>();
//        Set<String> uniqueSeriesIdSet = new HashSet<>();
//        List<Vod> vods = contentIdsList.stream().map(vodRepository::findByContentId).toList();
//        vods.stream().map(vodtoVodDtoWrapper::toVodDto).forEachOrdered(vodDto -> {
//            if (vodDto.getIsSeries() == 1 && vodDto.getSeriesId() != null) {
//                uniqueSeriesIdSet.add(vodDto.getSeriesId());
//            } else {
//                contentIds.add(vodDto.getContentId());
//
//            }
//        });
//        //시리즈물 별도 처리
//        for (String s : uniqueSeriesIdSet) {
//            String minContentId = this.convertToMinContentId(s);
//            contentIds.add(minContentId);
//        }
//        return contentIds;
//    }
//
//    //seriesId 파라미터로 받아서 -> 가장 작은 contentId 리턴하는 메서드
//    public String convertToMinContentId(String seriesId) {
//        List<Vod> vodList = vodRepository.findBySeriesId(seriesId);
//        List<String> contentIdsOfSeries = new ArrayList<>(vodList.stream().map(Vod::getContentId).toList());
//        Collections.sort(contentIdsOfSeries); //오름차순 정렬
//        return contentIdsOfSeries.get(0);
//    }
//
//}
