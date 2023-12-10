package com.example.VodReco.service.mainPage.getReco;

import com.example.VodReco.dto.client.MainResponseDto;
import com.example.VodReco.dto.model.toModel.ToModelDto;
import org.springframework.stereotype.Service;

@Service
public interface VodGetRecoService {


    MainResponseDto getAllContentIdsFromModel(String subsr);

    ToModelDto setDataForModel(String subsr);

    void parse(String recoResult);
}
