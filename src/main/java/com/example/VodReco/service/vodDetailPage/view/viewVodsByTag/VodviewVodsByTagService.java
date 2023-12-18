package com.example.VodReco.service.vodDetailPage.view.viewVodsByTag;

import com.example.VodReco.dto.vodDetail.TagsResponseDto;
import org.springframework.stereotype.Service;

@Service

public interface VodviewVodsByTagService {

    TagsResponseDto sendEachTagVods(String contentId);
}
