package com.example.VodReco.service;

import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.VodDto;
import org.springframework.stereotype.Service;

@Service
public class VodtoVodDto {
    public VodDto vodtoVodDto(Vod vod) {
        return vod.toVodDto(vod);
    }
}
