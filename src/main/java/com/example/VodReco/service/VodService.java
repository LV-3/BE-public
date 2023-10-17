package com.example.VodReco.service;

import com.example.VodReco.domain.UserRating;
import com.example.VodReco.domain.UserWish;
import com.example.VodReco.domain.Vod;
import com.example.VodReco.repository.RatingRepository;
import com.example.VodReco.repository.VodRepository;
import com.example.VodReco.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VodService {
    private final VodRepository vodRepository;
    private final WishRepository wishRepository;
    private final RatingRepository ratingRepository;

    @Autowired
    public VodService(VodRepository vodRepository, WishRepository wishRepository, RatingRepository ratingRepository) {
        this.vodRepository = vodRepository;
        this.wishRepository = wishRepository;
        this.ratingRepository = ratingRepository;
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

    // vcode로 Vod 조회
    public Vod getVod(String vcode) {
        return vodRepository.findByVcode(vcode);
    }


}
