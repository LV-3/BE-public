package com.example.VodReco.service;

import com.example.VodReco.domain.Vod;
import com.example.VodReco.domain.WishRating;
import com.example.VodReco.repository.VodRepository;
import com.example.VodReco.repository.WishRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VodService {
    private final VodRepository vodRepository;
    private final WishRatingRepository wishRatingRepository;

    @Autowired
    public VodService(VodRepository movieRepository, WishRatingRepository wishRatingRepository) {
        this.vodRepository = movieRepository;
        this.wishRatingRepository = wishRatingRepository;
    }
    //전체 VOD 조회
    public List<Vod> getAllVods(){
        return vodRepository.findAll();
    }

    //모든 posterUrl 조회
    public List<String> getAllPosterUrls(){
        List<String> posterUrls = new ArrayList<>();
        for (Vod vod: vodRepository.findAll()){
            String posterUrl = vod.getPosterurl();
            posterUrls.add(posterUrl);
        }
        return posterUrls;
    }

    // mcode로 Vod 조회
    public Vod getVod(String vcode) {
        return vodRepository.findByVcode(vcode);
    }

    // 컬럼: useremail / vcode / wish(0or1) / rating(0 or 1~5)
    public void saveWishRating(WishRating wishRating) {
        wishRatingRepository.save(wishRating);
    }

}
