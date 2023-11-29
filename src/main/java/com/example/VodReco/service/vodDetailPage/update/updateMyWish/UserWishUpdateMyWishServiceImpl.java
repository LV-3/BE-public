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

@Service
@RequiredArgsConstructor
public class UserWishUpdateMyWishServiceImpl implements UserWishUpdateMyWishService{
    private final UserWishRepository userWishRepository;
    private final UserWishViewRepository userWishViewRepository;
    private final VodRepository vodRepository;
    private final KafkaProducerService kafkaProducerService;
    private final KafkaConsumerService kafkaConsumerService;

    @Override
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
        //조회용 DB에 소비한 데이터 저장
//        //TODO: task가 delete인지, update인지, insert인지 구분해서 save나 deleteBy하는 메서드 별도 클래스로 분리 요망(231129)
        userWishViewRepository.save(UpdatedWishDto.toUserWishViewEntity(updatedWish));

        //[세연] kafka에 topic send, userWishViewRepository에 데이터 삽입 후
        //if (userWishViewRepository.findBy() 해서 이 함수의 파라미터와 같으면
        //그때 ResponseEntity.ok 띄우고 // 같지 않으면 에러 status 띄우기(231128)
    }
}
