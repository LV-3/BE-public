package com.example.VodReco.service.mainPage.getReco;

import com.example.VodReco.dto.VodDto;
import com.example.VodReco.dto.client.ToClient1stDto;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.util.Vod.VodtoVodDtoWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class VodReloadServiceImpl implements VodReloadService{

    private final VodRepository vodRepository;
    private final VodtoVodDtoWrapper vodtoVodDtoWrapper;

    @Override
    public List<ToClient1stDto> reloadRec(List<String> list) {
//    public List<ToClient1stDto> reloadDescriptionModelRec() {
//        System.out.println("content_id List 들어오나 확인 = " + list);
        return getToClient1stDtos(list);
    }

    private List<ToClient1stDto> getToClient1stDtos(List<String> DataList) {
        List<ToClient1stDto> list = new ArrayList<>();
        List<String> firstSeven = DataList.stream()
                .limit(7)
                .toList();
        try {
            DataList.subList(0, 7).clear();
            for (int j = 0; j < 7; j++) {
                String contentId = firstSeven.get(j);
                list.add(buildToClient1stDto(contentId));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
//            log.error("Exception ERROR: {} ", e.getMessage());
            throw e;
        }
    }

        //별도 테이블 만들면 이 메서드 수정(231126)
        public ToClient1stDto buildToClient1stDto(String contentId){
            VodDto vodDto = vodtoVodDtoWrapper.toVodDto(vodRepository.findByContentId(contentId));

            return ToClient1stDto.builder().contentId(contentId).posterurl(vodDto.getPosterurl()).title(vodDto.getTitle())
                    .mood(vodDto.getMood()).gpt_genres(vodDto.getGpt_genres()).gpt_subjects(vodDto.getGpt_subjects())
                    .build();
        }


    }
