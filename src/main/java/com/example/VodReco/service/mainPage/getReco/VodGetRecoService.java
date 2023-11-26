package com.example.VodReco.service.mainPage.getReco;

import com.example.VodReco.dto.model.toModel.ToModelDto;
import org.springframework.stereotype.Service;

@Service
public interface VodGetRecoService {
    ToModelDto getRecoFromModel(String subsr);
}
