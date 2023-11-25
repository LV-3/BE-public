package com.example.VodReco.service.vodDetailPage.wish.viewMyWish;

import com.example.VodReco.domain.UserWish;
import com.example.VodReco.dto.wish.ViewMyWishResponseDto;
import com.example.VodReco.repository.UserWishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserWishViewMyWishServiceImpl implements UserWishViewMyWishService {

    private final UserWishRepository userWishRepository;

    @Override
    public ViewMyWishResponseDto findMyWish(String subsr, String contentId){
        //findBySubrAndContentId는로 조회는 가능
        //현재 업데이트가 문제.
        // 최초 저장 시 1)PK를 직접 subsr+contentId로 만들든가 2) 변경 시 findBy And로 조회 -> delete -> save하기
        UserWish userWish = userWishRepository.findBySubsrAndContentId(subsr, contentId);
        //dto로 받기. 수정 요망(231124)

        if (userWish == null) {
            return null;
        }
        return userWish.toViewMyWishResponseDto(userWish);
    }
}
