package com.example.VodReco.service.mainPage.searchVods;

import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.VodDto;
import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.util.ContentIdToBasicInfoOfVodsWrapper;
import com.example.VodReco.util.Vod.VodtoVodDtoWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchVodServiceImpl implements SearchVodService{
    private final VodRepository vodRepository;
    private final MongoTemplate mongoTemplate;
    private final ContentIdToBasicInfoOfVodsWrapper contentIdToBasicInfoOfVodsWrapper;
    private final VodtoVodDtoWrapper vodtoVodDtoWrapper;


    @Autowired
    public SearchVodServiceImpl(VodRepository vodRepository, MongoTemplate mongoTemplate,ContentIdToBasicInfoOfVodsWrapper contentIdToBasicInfoOfVodsWrapper, VodtoVodDtoWrapper vodtoVodDtoWrapper) {
        this.vodRepository=vodRepository;
        this.mongoTemplate = mongoTemplate;
        this.contentIdToBasicInfoOfVodsWrapper = contentIdToBasicInfoOfVodsWrapper;
        this.vodtoVodDtoWrapper = vodtoVodDtoWrapper;

    }
    public List<VodDto> searchVods(String searchTerm) {
        // 사용자 입력 검색어의 공백 제거
        String searchTermWithoutSpaces = searchTerm.replaceAll("\\s+", "");

        // MongoDB에서 전체 데이터 가져오기
        List<Vod> allVods = mongoTemplate.findAll(Vod.class);

        // 공백 제거한 검색어와 데이터베이스의 값을 비교하여 검색하기
        List<Vod> foundVods = new ArrayList<>();
        for (Vod vod : allVods) {
            // 값이 null이면 continue하여 다음 루프로 넘어감
            if (vod.getActors() == null) {
                continue;
            }

            String titleWithoutSpaces = vod.getTitle().replaceAll("\\s+", "");
            String actorsWithoutSpaces = vod.getActors().replaceAll("\\s+", "");

            if (titleWithoutSpaces.toLowerCase().contains(searchTermWithoutSpaces.toLowerCase())
                    || actorsWithoutSpaces.toLowerCase().contains(searchTermWithoutSpaces.toLowerCase())) {
                foundVods.add(vod);
            }
        }

        return foundVods.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());


//        String searchTermWithoutSpaces = searchTerm.replaceAll("\\s+", "");
//        Criteria criteria = new Criteria();
//        criteria.orOperator(
//                Criteria.where("title").regex(".*" + searchTermWithoutSpaces + ".*", "i"),
//                Criteria.where("actors").regex(".*" + searchTermWithoutSpaces + ".*", "i")
//        );
//
//        Query query = new Query(criteria);
//
//        List<Vod> vods = mongoTemplate.find(query, Vod.class);
//
//        return vods.stream()
//                .map(this::mapToDto)
//                .collect(Collectors.toList());
    }

    private VodDto mapToDto(Vod vod) {
        // Entity를 DTO로 변환하는 로직
        return VodDto.builder()
                .contentId(vod.getContentId())
                .title(vod.getTitle())
                .posterurl(vod.getPosterurl())
                .build();
    }

    public List<BasicInfoOfVodDto> getBasicInfoOfVods(List<String> contentIds) {
        List<BasicInfoOfVodDto> basicInfoList = new ArrayList<>();
        return contentIdToBasicInfoOfVodsWrapper.getBasicInfoOfVodDtos(basicInfoList, contentIds, vodtoVodDtoWrapper, vodRepository);
    }
//    @Override
//    public List<VodDto> searchVods(String searchTerm) {
//        if (searchTerm == null || searchTerm.trim().isEmpty()) {
//            return Collections.emptyList();
//        }
//
//        // 사용자 입력 검색어의 각 단어를 분리
//        String[] searchWords = searchTerm.split("\\s+");
//
//        // 검색 결과를 저장할 Set 초기화
//        Set<Vod> combinedVods = new HashSet<>();
//
//        // 각 단어를 포함하는 결과를 찾아 합치기
//        for (String word : searchWords) {
//            // 각 단어를 포함하는 VOD 검색 (제목과 배우명에 대해)
//            List<Vod> vodEntitiesByTitle = vodRepository.findByTitleIgnoreCaseContaining(word);
//            List<Vod> vodEntitiesByActors = vodRepository.findByActorsIgnoreCaseContaining(word);
//
//            // 결과를 Set에 추가
//            combinedVods.addAll(vodEntitiesByTitle);
//            combinedVods.addAll(vodEntitiesByActors);
//        }
////        if (searchTerm == null) {
////            return Collections.emptyList();
////        }
//
////        // 사용자 입력 검색어의 공백 제거
////        String searchTermWithoutSpaces = searchTerm.replaceAll("\\s+", "");
////
////        // 제목에 포함하는 VOD 검색 (공백 제거)
////        List<Vod> vodEntitiesByTitle = vodRepository.findByTitleIgnoreCaseContaining(searchTermWithoutSpaces);
////
////        // 배우명에 포함하는 VOD 검색 (공백 제거)
////        List<Vod> vodEntitiesByActors = vodRepository.findByActorsIgnoreCaseContaining(searchTermWithoutSpaces);
////
////        // 두 결과를 합친 후 중복 제거하여 DTO로 변환하여 반환
////        Set<Vod> combinedVods = new HashSet<>();
////        combinedVods.addAll(vodEntitiesByTitle);
////        combinedVods.addAll(vodEntitiesByActors);
//
//        return combinedVods.stream()
//                .map(this::mapToDto)
//                .collect(Collectors.toList());
//    }
//
//    private VodDto mapToDto(Vod vod) {
//        // Entity를 DTO로 변환하는 로직
//        return VodDto.builder()
//                .contentId(vod.getContentId())
//                .title(vod.getTitle())
//                .posterurl(vod.getPosterurl())
//                .actors(vod.getActors())
//                .build();
//    }
}
