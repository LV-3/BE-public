package com.example.VodReco.service.mainPage.getReco;

import com.example.VodReco.dto.client.MainResponseDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface VodGetRecoService {

    Mono<MainResponseDto> getAllContentIdsFromModel(String subsr);

}
