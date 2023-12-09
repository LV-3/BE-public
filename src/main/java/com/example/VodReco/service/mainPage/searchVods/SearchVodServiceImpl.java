package com.example.VodReco.service.mainPage.searchVods;

import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.VodDto;
import com.example.VodReco.mongoRepository.VodRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class SearchVodServiceImpl implements SearchVodService{
    private final VodRepository vodRepository;

    public SearchVodServiceImpl(VodRepository vodRepository) {
        this.vodRepository = vodRepository;
    }
//    @Override
//    public List<VodDto> searchVods(String searchTerm) {
//        if (searchTerm == null || searchTerm.trim().isEmpty()) {
//            return Collections.emptyList();
//        }
//
//        // 모든 공백을 제거한 검색어
//        String searchTermWithoutSpaces = searchTerm.replaceAll("\\s+", "");
//
//        // 정확한 일치 검색
//        List<Vod> exactMatchByTitle = vodRepository.findByExactTitle(searchTerm);
//        List<Vod> exactMatchByActors = vodRepository.findByExactActors(searchTerm);
//
//        // 부분 일치 검색
//        List<Vod> partialMatchByTitleOrActors = vodRepository.findByTitleOrActors(searchTermWithoutSpaces);
//
//        // 중복 제거하고 합치기
//        Set<Vod> combinedVods = new HashSet<>();
//        combinedVods.addAll(exactMatchByTitle);
//        combinedVods.addAll(exactMatchByActors);
//        combinedVods.addAll(partialMatchByTitleOrActors);
//
//        return combinedVods.stream()
//                .map(this::mapToDto)
//                .collect(Collectors.toList());
//    }
    @Override
    public List<VodDto> searchVods(String searchTerm) {
        if (searchTerm == null) {
            return Collections.emptyList();
        }

        searchTerm = searchTerm.trim();
        // 정규식 패턴 생성
        Pattern pattern = Pattern.compile(".*" + searchTerm + ".*", Pattern.CASE_INSENSITIVE);

        // 제목에 포함하는 VOD 검색
        List<Vod> vodEntitiesByTitle = vodRepository.findByTitleContainingIgnoreCase(searchTerm);

        // 배우명에 포함하는 VOD 검색
        List<Vod> vodEntitiesByActors = vodRepository.findByActorsIgnoreCaseContaining(searchTerm);

        // 두 결과를 합친 후 중복 제거하여 DTO로 변환하여 반환
        Set<Vod> combinedVods = new HashSet<>();
        combinedVods.addAll(vodEntitiesByTitle);
        combinedVods.addAll(vodEntitiesByActors);

        return combinedVods.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private VodDto mapToDto(Vod vod) {
        // Entity를 DTO로 변환하는 로직
        return VodDto.builder()
                .contentId(vod.getContentId())
                .title(vod.getTitle())
                .posterurl(vod.getPosterurl())
                .actors(vod.getActors())
                .build();
    }
}
