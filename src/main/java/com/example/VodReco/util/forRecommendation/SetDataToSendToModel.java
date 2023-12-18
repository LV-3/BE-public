//package com.example.VodReco.util.forRecommendation;
//
//import com.example.VodReco.domain.ForDeepFM;
//import com.example.VodReco.domain.UserWatchTotal;
//import com.example.VodReco.domain.Vod;
//import com.example.VodReco.dto.Recommendation.model.toModel.*;
//import com.example.VodReco.mongoRepository.ForDeepFMRepository;
//import com.example.VodReco.mongoRepository.UserWatchTotalRepository;
//import com.example.VodReco.mongoRepository.VodRepository;
//import com.example.VodReco.util.ForDeepFM.ToForDeepFMDtoWrapper;
//import com.example.VodReco.util.StringToListWrapper;
//import com.example.VodReco.util.Vod.VodtoVodDtoWrapper;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class SetDataToSendToModel {
//    private final UserWatchTotalRepository userWatchTotalRepository;
//    private final VodRepository vodRepository;
//    private final VodtoVodDtoWrapper vodtoVodDtoWrapper;
//    private final StringToListWrapper stringToListWrapper;
//    private final ForDeepFMRepository forDeepFMRepository;
//    private final ToForDeepFMDtoWrapper toForDeepFMDtoWrapper;
//
//    private final ObjectMapper objectMapper;
//
//
//    public ToModel2ndDto setDataForModel(String subsr) {
//        List<EveryDescriptionDto> descriptionResponseList = new ArrayList<>();
//        List<EveryMoodDto> moodResponseList = new ArrayList<>();
//        List<EveryPersonalDto> personalResponseList = new ArrayList<>();
//
//        //mood는 null들어올 경우 NPE(231127)
//        for (UserWatchTotal v : userWatchTotalRepository.findBySubsr(subsr)) {
//            String contentId = v.getContentId();
//            Vod byContentId = vodRepository.findByContentId(contentId);
//
//            EveryDescriptionDto everyDescriptionDto;
//            //시리즈물은 SMRY 대신 TITLE_SMRY 전송
////            if (byContentId.getIsSeries() == 1) {
////                everyDescriptionDto = EveryDescriptionDto.builder()
////                        .content_id(contentId).description(vodtoVodDtoWrapper.toVodDto(byContentId).getTitleDescription())
////                        .build();
////            } else {
//                everyDescriptionDto = EveryDescriptionDto.builder()
//                        .content_id(contentId).description(vodtoVodDtoWrapper.toVodDto(byContentId).getDescription())
//                        .build();
////            }
//            descriptionResponseList.add(everyDescriptionDto);
//
//            EveryMoodDto everyMoodDto = EveryMoodDto.builder()
//                    .content_id(contentId).mood(stringToListWrapper.stringToList(byContentId.getMood()))
//                    .build();
//            moodResponseList.add(everyMoodDto);
//        }
//        for (ForDeepFM f : forDeepFMRepository.findBySubsr(subsr)) {
//            ForDeepFMDto forDeepFMDto = toForDeepFMDtoWrapper.toForDeepFMDto(f);
//            //liked가 1인 데이터만 personalResponseList에 담기
////            if (forDeepFMDto.getLiked() == 1) {
//            EveryPersonalDto everyPersonalDto = EveryPersonalDto.builder().subsr(Integer.parseInt(subsr)).content_id(Integer.parseInt(forDeepFMDto.getContentId())).ct_cl(forDeepFMDto.getCategory())
//                    .genre_of_ct_cl(forDeepFMDto.getGenre()).template_A_TopGroup(forDeepFMDto.getMood()).template_B_TopGroup(forDeepFMDto.getGpt_genres()).template_C_TopGroup(forDeepFMDto.getGpt_subjects())
//                    .user_preference(forDeepFMDto.getUserPreference())
//                    .timeGroup(forDeepFMDto.getTimeGroup())
//                    .build();
//            personalResponseList.add(everyPersonalDto);
//        }
//        ToModelDto toModelDto = ToModelDto.builder()
//                .description_data(descriptionResponseList)
//                .mood_data(moodResponseList)
////                .personal_data(new ArrayList<>())
//                .personal_data(personalResponseList)
//                .build();
//        return ToModel2ndDto.builder().dataForModel(toModelDto).build();
//
//    }
//
//}
