package com.example.VodReco.service.vodDetailPage.view.viewDetailInfo;

import com.example.VodReco.dto.vodDetail.VodDetailResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface VodViewDetailInfoService {
    VodDetailResponseDto getVodByContentId(String contentId);
}
