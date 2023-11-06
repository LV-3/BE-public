package com.example.VodReco.dto.client;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ToClient2ndDto {
    private ToClient1stDto vod1;
    private ToClient1stDto vod2;
    private ToClient1stDto vod3;
    private ToClient1stDto vod4;
    private ToClient1stDto vod5;
    private ToClient1stDto vod6;
    private ToClient1stDto vod7;
    private ToClient1stDto vod8;
    private ToClient1stDto vod9;
    private ToClient1stDto vod10;

    @Builder
    public ToClient2ndDto(ToClient1stDto vod1, ToClient1stDto vod2, ToClient1stDto vod3, ToClient1stDto vod4,
                          ToClient1stDto vod5, ToClient1stDto vod6, ToClient1stDto vod7, ToClient1stDto vod8, ToClient1stDto vod9, ToClient1stDto vod10) {
        this.vod1 = vod1;
        this.vod2 = vod2;
        this.vod3 = vod3;
        this.vod4 = vod4;
        this.vod5 = vod5;
        this.vod6 = vod6;
        this.vod7 = vod7;
        this.vod8 = vod8;
        this.vod9 = vod9;
        this.vod10 = vod10;



    }
}
