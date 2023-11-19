package com.example.VodReco.service;

import com.example.VodReco.dto.VodDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VodService {

    List<VodDto> getAllVods();

    List<String> getAllPosterUrls();


}
