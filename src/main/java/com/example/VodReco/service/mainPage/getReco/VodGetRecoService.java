package com.example.VodReco.service.mainPage.getReco;

import com.example.VodReco.dto.client.MainResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface VodGetRecoService {

    MainResponseDto getAllContentIdsFromModel(String subsr);

}
