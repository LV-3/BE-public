package com.example.VodReco.service.mainPage.searchVods;

import com.example.VodReco.domain.Vod;
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
public class SearchVodServiceImpl implements SearchVodService {
    private final VodRepository vodRepository;
    private final MongoTemplate mongoTemplate;
    private final ContentIdToBasicInfoOfVodsWrapper contentIdToBasicInfoOfVodsWrapper;
    private final VodtoVodDtoWrapper vodtoVodDtoWrapper;


    @Autowired
    public SearchVodServiceImpl(VodRepository vodRepository, MongoTemplate mongoTemplate, ContentIdToBasicInfoOfVodsWrapper contentIdToBasicInfoOfVodsWrapper, VodtoVodDtoWrapper vodtoVodDtoWrapper) {
        this.vodRepository = vodRepository;
        this.mongoTemplate = mongoTemplate;
        this.contentIdToBasicInfoOfVodsWrapper = contentIdToBasicInfoOfVodsWrapper;
        this.vodtoVodDtoWrapper = vodtoVodDtoWrapper;

    }
    @Override
    public List<BasicInfoOfVodDto> searchVods(String searchTerm) {
        String searchTermWithoutSpaces = searchTerm.replaceAll("\\s+", "");

        // MongoDB에서 전체 데이터 가져오기
        List<Vod> allVods = mongoTemplate.findAll(Vod.class);

        // 검색어와 매칭되는 Vod 찾기
        List<Vod> foundVods = allVods.stream()
                .filter(vod -> {
                    if (vod.getActors() == null) {
                        return false;
                    }
                    String titleWithoutSpaces = vod.getTitle().replaceAll("\\s+", "");
                    String actorsWithoutSpaces = vod.getActors().replaceAll("\\s+", "");
                    return titleWithoutSpaces.toLowerCase().contains(searchTermWithoutSpaces.toLowerCase())
                            || actorsWithoutSpaces.toLowerCase().contains(searchTermWithoutSpaces.toLowerCase());
                })
                .collect(Collectors.toList());

        // 검색된 Vod들의 contentId 추출
        List<String> contentIds = foundVods.stream().map(Vod::getContentId).toList();

        // ContentIdToBasicInfoOfVodsWrapper를 사용하여 필요한 정보로 변환
        List<BasicInfoOfVodDto> basicInfoList = new ArrayList<>();
        return contentIdToBasicInfoOfVodsWrapper.getBasicInfoOfVodDtos(basicInfoList, contentIds, vodtoVodDtoWrapper, vodRepository);
    }

//    public List<VodDto> searchVods(String searchTerm) {
//        // 사용자 입력 검색어의 공백 제거
//        String searchTermWithoutSpaces = searchTerm.replaceAll("\\s+", "");
//
//        // MongoDB에서 전체 데이터 가져오기
//        List<Vod> allVods = mongoTemplate.findAll(Vod.class);
//
//        // 공백 제거한 검색어와 데이터베이스의 값을 비교하여 검색하기
//        List<Vod> foundVods = new ArrayList<>();
//        for (Vod vod : allVods) {
//            // 값이 null이면 continue하여 다음 루프로 넘어감
//            if (vod.getActors() == null) {
//                continue;
//            }
//
//            String titleWithoutSpaces = vod.getTitle().replaceAll("\\s+", "");
//            String actorsWithoutSpaces = vod.getActors().replaceAll("\\s+", "");
//
//            if (titleWithoutSpaces.toLowerCase().contains(searchTermWithoutSpaces.toLowerCase())
//                    || actorsWithoutSpaces.toLowerCase().contains(searchTermWithoutSpaces.toLowerCase())) {
//                foundVods.add(vod);
//            }
//        }
//
//        return foundVods.stream()
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
//                .build();
//    }

}


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



