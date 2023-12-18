package com.example.VodReco.dto.vodDetail;

import com.example.VodReco.dto.BasicInfoOfVodDto;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class TagsResponseDto {
    private List<String> tags;
    private List<BasicInfoOfVodDto> tag1;
    private List<BasicInfoOfVodDto> tag2;
    private List<BasicInfoOfVodDto> tag3;

    @Builder
    public TagsResponseDto(List<String> tags, List<BasicInfoOfVodDto> tag1, List<BasicInfoOfVodDto> tag2, List<BasicInfoOfVodDto> tag3) {
        this.tags = tags;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
    }
}
