package com.example.VodReco.controller;

import com.example.VodReco.dto.*;
import com.example.VodReco.dto.client.ToClient1stDto;
import com.example.VodReco.dto.client.ToClient2ndDto;
import com.example.VodReco.dto.client.ToClient3rdDto;
import com.example.VodReco.dto.model.*;
import com.example.VodReco.jwt.TokenProvider;
import com.example.VodReco.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor

@Slf4j


@RequestMapping("/main")
public class MainController {
    private final KafkaProducerService producerService;
    private final KafkaConsumerService consumerService;

    private final TokenProvider tokenProvider;
    private final VodServiceImpl vodServiceImpl;
    private final WishServiceImpl wishServiceImpl;
    private final RatingServiceImpl ratingServiceImpl;

    private final List<EveryDescription> descriptionList = new ArrayList<>();
    private final List<EveryGenre> genreList = new ArrayList<>();

    //테스트용(231112)
    private final List<String> personalList = new ArrayList<String>();

    //    //중대 자연재해: 이 아래 list들이 다 전역변수긴 한데 compile 새로 할 때마다 비워진다
//    //테스트 시 곤란한데(231104) 실제 서비스 시에는 서버 다시 돌릴 일 없으니 괜찮은가?
//    private final List<String> descriptionModelList = new ArrayList<>();
//    private final List<String> genreModelList = new ArrayList<>();
//    private final List<String> personalModelList = new ArrayList<>();


//    private final ToDescriptionModelDto toDescriptionModelDto; //bean 등록함(231104)
//    private final ToGenreModelDto toGenreModelDto; // bean 등록함
//    private final ToPersonalModelDto toPersonalModelDto; // bean 등록, 테스트용(231112)

    private final ToModelDto toModelDto; // bean등록함(231112)

    //1. 새로고침 클릭 이전 프론트로부터 데이터 받아 DB에 적재 + 모델에 보낼 대기조 만드는 메서드(231104)
    //스프링 빈에 모델에 보내기 위한 dto 3개 등록해서 전역변수로 써야 할 것으로 예상
    //이 메서드에서는 각각 담아놓고 -> 새로고침 터지면 아래 메서드에서 꺼내서 정리
    /*
     *  프론트가 보내는 데이터 형식:
     *     {"model”:"descriptionModel”, “content_id”:”20200622”, “wish”:1}
     *     wishRequestDto, ratingRequestDto에 담아서 model필드랑 content_id만 따로 담기
     *     일단 찜을 취소하면 "wish":0으로 받는다고 생각하기 -> 논의 필요. 그래도 찜을 취소한 건 제외하는 게(231104)
     *
     */


    //카프카 도입으로 흐름 수정(231112)
    //새로고침 버튼이 눌리면 준비해둔 데이터를 토픽에 보내고 -> fastAPI에서 토픽에 던지는 데이터를 기다렸다가 받아와서 -> 프론트에 전송해야 하는데
    //wish, rating 들어올 때 DB에 적재함과 동시에 + 모델에 보낼 형식 맞춰 준비해서 -> 새로고침과 다음 추천 사이 로딩타임을 최소화
    @PostMapping("/{content_id}/wish")

    public List<EveryDescription> wish(@PathVariable("content_id") String contentId, @RequestBody WishRequestFromMainDto wishRequestFromMainDto, ServletRequest servletRequest)
            throws ServletException, IOException {
        //먼저 DB 업데이트
        WishResponseDto wishResponseDto = WishResponseDto.builder().email(tokenProvider.getEmailFromToken(servletRequest))
                .contentId(contentId).wish(wishRequestFromMainDto.getWish()).build();
//      확인
        System.out.println("찜 = " + wishResponseDto.getWish());
        wishServiceImpl.saveWish(wishResponseDto.toWishEntity(wishResponseDto));
//      테스트
        System.out.println(this.wishServiceImpl.findUserWishByContentId(contentId));

        //모델이름, content_id 따로 담아놓기
        //모든 모델에서 들어오는 데이터 같이 담았다가 새로고침 눌리면 분류하는 것보다
        //이때 미리 나눠 담아두는 게 새로고침 후 로딩타임 줄일 수 있다 판단함(231104)
        if (wishRequestFromMainDto.getModel().equals("descriptionModel")) {
//            descriptionModelList.add(wishRequestFromMainDto.getContentId());
            String c = wishRequestFromMainDto.getContentId();
            System.out.println(c); //확인용
            EveryDescription everyDescription = EveryDescription.builder()
                    .content_id(vodServiceImpl.getVodByContentId(c).getContentId()).description(vodServiceImpl.getVodByContentId(c).getDescription()).build();
            descriptionList.add(everyDescription);
            //list까지만 만들어놓고 새로고침 터지면 ToDescriptionModelDto의 description_data 필드에 set해서 보내기(231112)
        } else if (wishRequestFromMainDto.getModel().equals("genreModel")) {
//            genreModelList.add(wishRequestFromMainDto.getContentId());
            String c = wishRequestFromMainDto.getContentId();
            EveryGenre everyGenre = EveryGenre.builder()
                    .content_id(vodServiceImpl.getVodByContentId(c).getContentId()).genre(vodServiceImpl.getVodByContentId(c).getGenre()).build();
            genreList.add(everyGenre);
        } else {
            personalList.add(wishRequestFromMainDto.getContentId());
        }
        //테스트용 리턴, 추후 void로 변경하고 삭제
//        return descriptionModelList;
        return descriptionList;
    }

    @PostMapping("/{content_id}/rating")
    public List<EveryDescription> rating(@PathVariable("content_id") String contentId, @RequestBody RatingRequestFromMainDto ratingRequestFromMainDto, ServletRequest servletRequest)
            throws ServletException, IOException {
        RatingResponseDto ratingResponseDto = RatingResponseDto.builder().email(tokenProvider.getEmailFromToken(servletRequest))
                .contentId(contentId).rating(ratingRequestFromMainDto.getRating()).comment(ratingRequestFromMainDto.getComment()).build();
//            확인
        System.out.println("평점 = " + ratingResponseDto.getRating());
        System.out.println("댓글 = " + ratingResponseDto.getComment());

        ratingServiceImpl.saveRating(ratingResponseDto.toRatingEntity(ratingResponseDto));

//        테스트
        System.out.println(this.ratingServiceImpl.findUserRatingByContentId(contentId));

        //모델이름, content_id 따로 담아놓기
        if (ratingRequestFromMainDto.getModel().equals("descriptionModel")) {
//            descriptionModelList.add(wishRequestFromMainDto.getContentId());
            String c = ratingRequestFromMainDto.getContentId();
            EveryDescription everyDescription = EveryDescription.builder()
                    .content_id(vodServiceImpl.getVodByContentId(c).getContentId()).description(vodServiceImpl.getVodByContentId(c).getDescription()).build();
            descriptionList.add(everyDescription);
            //list까지만 만들어놓고 새로고침 터지면 ToDescriptionModelDto의 description_data 필드에 set해서 보내기(231112)
        } else if (ratingRequestFromMainDto.getModel().equals("genreModel")) {
//            genreModelList.add(wishRequestFromMainDto.getContentId());
            String c = ratingRequestFromMainDto.getContentId();
            EveryGenre everyGenre = EveryGenre.builder()
                    .content_id(vodServiceImpl.getVodByContentId(c).getContentId()).genre(vodServiceImpl.getVodByContentId(c).getGenre()).build();
            genreList.add(everyGenre);
        } else {
            personalList.add(ratingRequestFromMainDto.getContentId());
        }
        //테스트용 리턴, 추후 void로 변경하고 삭제
//        return descriptionModelList;
        return descriptionList;
    }

//! 중요! 새로고침 이벤트 터지면 globalList에서 모든 데이터 넘기고 clearAll 해줘야함!! 이거 안 하면 그날로 송장됨


    //2. 새로고침 클릭 이후 대기하던 데이터를 형식 맞춰 모델에 보내는 메서드 3개(231104)

    //모델에 보내는 데이터 형식:

    //descriptionModel
    //"description_data" : [{"content_id":"value1","description":"value2"},
    //									{"content_id":"value3","description":"value4"}] 이렇게 3개


//    @GetMapping("/click-desc")
//    public ToDescriptionModelDto sendDescriptionData() {
//        System.out.println(descriptionModelList); // 추후 삭제
//        List<EveryDescription> list = new ArrayList<>();
//        for (String s : descriptionModelList) {
//            EveryDescription everyDescription = EveryDescription.builder()
//                    .content_id(vodServiceImpl.getVodByContentId(s).getContentId()).description(vodServiceImpl.getVodByContentId(s).getDescription()).build();
//            list.add(everyDescription);
//        }
//        toModelDescriptionDto.setDescription_data(list);
//        return toModelDescriptionDto;
//    }

    //genreModel
    //"genre_data" : [{"content_id":"value1","genre":["genre1", "genre2", ...]},
    //									{"content_id":"value3","genre":["genre1", "genre2", ...]}]

//    @GetMapping("/click-gen") //같은 endpoint에 여러 개 매핑은 불가능. url 여러 개 써야 함(231104)
//    public ToGenreModelDto sendGenreData() {
//        System.out.println(genreModelList); //확인용. 추후 삭제
//        List<EveryGenre> list = new ArrayList<>();
//        for (String s : genreModelList) {
//            EveryGenre everyGenre = EveryGenre.builder()
//                    .content_id(vodServiceImpl.getVodByContentId(s).getContentId()).genre(vodServiceImpl.getVodByContentId(s).getGenre()).build();
//            list.add(everyGenre);
//        }
//        toGenreModelDto.setGenre_data(list); //어? setGenre_data 언더바 껴있는데 되네 왜지?
//        return toGenreModelDto;
//    }

    //personalModel
    //데이터 형식 미정(231104)


    //~작성 예정~


    //3. 새로고침 클릭 이후 모델로부터 데이터 받아서 -> 형식 맞추고 -> 프론트에 보내는 메서드(231104)

    //모델이 보내는 데이터 형식:
    //{"description_data":["content_id1", "content_id2", ... , "content_id10" ],
    // "genre_data":["content_id1", "content_id2", ... , "content_id10"],
    // "personal_data":["content_id1", "content_id2", ... , "content_id10"]}
    //프론트에 보낼 데이터 형식: 노션에 있음


    //순서: 스프링 서버 재실행 -> Producer 파이썬 파일 실행 -> API 테스트
    //새로고침 이벤트 한 번에 아래 method까지 병합해야 함 -> 방안 1. fastAPI에 데이터 3가지 그냥 묶어서 보낸다 2. API 3개로 분리하고 셋 중에 아무거나에 아래 메서드 연결한다(기준이?)
    //fastAPI에 send하는 건 return으로 줄 필요 없고 중간에 보내버리면 됨(231110)
    @GetMapping("/byFastAPI")
    public ToClient3rdDto getFromModel(){
//        toDescriptionModelDto.setDescription_data(descriptionList);
//        toGenreModelDto.setGenre_data(genreList);
//        toPersonalModelDto.setGenre_data(genreList); // 테스트용(231112)


        //Topic에 데이터 전송
        // personal모델 추후 작성 필요(231112)
        toModelDto.setModelName("description_data");
        toModelDto.setResponseData(descriptionList);
        producerService.sendMessage(toModelDto);

        toModelDto.setModelName("genre_data");
        toModelDto.setResponseData(genreList);
        producerService.sendMessage(toModelDto);

        descriptionList.clear(); //다음 턴을 위해 리스트 비우기(231112)
        genreList.clear();


        //이하 consumer에서 가져온 데이터 처리해서 프론트로 보내는 코드(231112)
//        초기에 Topic에 데이터 없으면 NPE(231112) -> exception 처리 필요

        FromModelDto fromModelDto = consumerService.getProcessedData();
        System.out.println("컨트롤러 확인 = " + fromModelDto);


        ToClient1stDto[] array1 = new ToClient1stDto[10];
        List<String> descriptionData = fromModelDto.getDescription_data();
        ToClient2ndDto descriptionDto = sendData(array1, descriptionData);


        ToClient1stDto[] array2 = new ToClient1stDto[10];
        List<String> genreData = fromModelDto.getGenre_data();
        ToClient2ndDto genreDto = sendData(array2, genreData);

        ToClient1stDto[] array3 = new ToClient1stDto[10];
        List<String> personalData = fromModelDto.getPersonal_data();
        ToClient2ndDto personalDto = sendData(array3, personalData);

        return ToClient3rdDto.builder().description_data(descriptionDto)
                .genre_data(genreDto).personal_data(personalDto).build();

    }

    private ToClient2ndDto sendData(ToClient1stDto[] array, List<String> data) {
        for (int i = 0; i < 10; i++) {
            ToClient1stDto toClient1stDto = ToClient1stDto.builder().contentId(data.get(i))
                    .posterurl(vodServiceImpl.getVodByContentId(data.get(i)).getPosterurl()).build();
            array[i] = toClient1stDto;
        }
        return ToClient2ndDto.builder().vod1(array[0]).vod2(array[1]).vod3(array[2])
                .vod4(array[3]).vod5(array[4]).vod6(array[5]).vod7(array[6]).vod8(array[6]).vod9(array[8]).vod10(array[9]).build();
    }
}

