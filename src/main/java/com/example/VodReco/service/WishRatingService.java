package com.example.VodReco.service;

import com.example.VodReco.repository.WishRatingRepository;
import org.springframework.stereotype.Service;

@Service
public class WishRatingService {
    private final WishRatingRepository wishRatingRepository;
    public WishRatingService(WishRatingRepository wishRatingRepository) {
        this.wishRatingRepository = wishRatingRepository;
    }


}
