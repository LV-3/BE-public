package com.example.VodReco.service.mainPage.getReco;

import com.example.VodReco.dto.client.ToClient1stDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VodReloadService {
    List<ToClient1stDto> reloadRec(List<String> list);
}
