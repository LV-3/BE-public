package com.example.VodReco.service.mainPage.getReco;

import com.example.VodReco.dto.VodDto;
import com.example.VodReco.dto.client.ToClient1stDto;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.util.Vod.VodtoVodDtoWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
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

        public ToClient1stDto buildToClient1stDto(String contentId){
            VodDto vodDto = vodtoVodDtoWrapper.toVodDto(vodRepository.findByContentId(contentId));
//            프론트엔드에 mood,gpt_genres, gpt_subjects 하나씩만 보내기 위해 subList(0,1) 이용 (231206)

//            mood, gpt_subjects, gpt_genres가 null일 경우 간단한 null처리. 추후 리팩토링 (231206)
//            List<String> gpt_subjects = new ArrayList<>();
//            List<String> gpt_genres = new ArrayList<>();
//            List<String> mood = new ArrayList<>();
//
//            if (vodDto.getMood() != null && !vodDto.getMood().isEmpty()) {
//                mood.add(vodDto.getMood().get(0));
//            }
//            if (vodDto.getGpt_subjects() != null && !vodDto.getGpt_subjects().isEmpty()) {
//                gpt_subjects.add(vodDto.getGpt_subjects().get(0));
//            }
//            if (vodDto.getGpt_genres() != null && !vodDto.getGpt_genres().isEmpty()) {
//                gpt_genres.add(vodDto.getGpt_genres().get(0));
//            }

            List<String> gpt_subjects = new ArrayList<>();
            List<String> gpt_genres = new ArrayList<>();
            List<String> mood = new ArrayList<>();
            if (!vodDto.getUniqueTemplates().isEmpty()) {
                String template1 = vodDto.getUniqueTemplates().get(0);
                String template2 = vodDto.getUniqueTemplates().get(1);
                String template3 = vodDto.getUniqueTemplates().get(2);

                mood.add(template1);
                gpt_genres.add(template2);
                gpt_subjects.add(template3);

            }


            return ToClient1stDto.builder().contentId(contentId).posterurl(vodDto.getPosterurl()).title(vodDto.getTitle())
                    .mood(mood).gpt_genres(gpt_genres).gpt_subjects(gpt_subjects)
                    .build();
        }

    public void checkIfNotTranslated(List<String> list, String templateWord) {
        if templateWord.
    }


    }
