package com.example.VodReco.service;

import com.example.VodReco.repository.WishRatingRepository;
import com.example.VodReco.repository.WishRepository;
import org.springframework.stereotype.Service;

@Service
public class WishService {
    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }
}
