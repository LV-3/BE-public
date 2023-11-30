package com.example.VodReco.service.vodDetailPage.update.updateMyWish;

import com.example.VodReco.dto.CQRS.UpdatedWishDto;
import com.example.VodReco.dto.wish.UpdateMyWishDto;
import com.example.VodReco.dto.wish.UpdateMyWishRequestDto;
import com.example.VodReco.mongoRepository.UserWishViewRepository;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.repository.UserWishRepository;
import com.example.VodReco.service.KafkaConsumerService;
import com.example.VodReco.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserWishUpdateMyWishServiceImpl implements UserWishUpdateMyWishService{
    private final UserWishRepository userWishRepository;
    private final UserWishViewRepository userWishViewRepository;
    private final VodRepository vodRepository;
    private final KafkaProducerService kafkaProducerService;
    private final KafkaConsumerService kafkaConsumerService;

    @Override
    @Transactional //메서드 내 처리가 실패하면 Rollback
    //카프카 도입 시 컨슈머에서 NPE터지는 이유: 카프카는 기본적으로 비동기. 토픽이 여러 개면 서로 관련 없이 동작하고 + 한 개의 토픽이어도 보내고 받는 게 순차적으로 처리되지 않음.
    //이 경우 producer가 데이터를 send하기도 전에 consumer가 데이터를 소비하려고 해서 save에서 NPE
    //프로젝트가 분리된 게 아니라면 굳이 kafka 쓸 필요 없음. 한 번에 두 레포지토리(=두 DB)에 save하면 됨
    //Q. 두 save 중 하나라도 실패하면 두 DB의 동일성을 보장하지 못하지 않나? A. @Transactional 붙이면 해결.
    public void saveWish(UpdateMyWishRequestDto updateMyWishRequestDto, String contentId) {
        String uniqueId = updateMyWishRequestDto.getSubsr() + contentId;
        UpdateMyWishDto updateMyWishDto = UpdateMyWishDto.builder().uniqueId(uniqueId).subsr(updateMyWishRequestDto.getSubsr())
                .contentId(contentId).wish(updateMyWishRequestDto.getWish()).title(vodRepository.findByContentId(contentId).getTitle()).
                posterurl(vodRepository.findByContentId(contentId).getPosterurl()).build();
        userWishRepository.save(updateMyWishDto.toWishEntity(updateMyWishDto));

        //변경된 데이터 토픽에 send
        UpdatedWishDto wishUpdate = UpdatedWishDto.builder().task("wish update").updateMyWishDto(updateMyWishDto).build();
        kafkaProducerService.sendWish(wishUpdate);
//        변경된 데이터 소비
        UpdatedWishDto updatedWish = kafkaConsumerService.getUpdatedWish();
        System.out.println("컨슈머 테스트 = " + updatedWish);
        //조회용 DB에 소비한 데이터 저장
//        //TODO: task가 delete인지, update인지, insert인지 구분해서 save나 deleteBy하는 메서드 별도 클래스로 분리 요망(231129)
        userWishViewRepository.save(UpdatedWishDto.toUserWishViewEntity(updatedWish));

        //[세연] kafka에 topic send, userWishViewRepository에 데이터 삽입 후
        //if (userWishViewRepository.findBy() 해서 이 함수의 파라미터와 같으면
        //그때 ResponseEntity.ok 띄우고 // 같지 않으면 에러 status 띄우기(231128)
    }
}
