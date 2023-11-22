package com.example.VodReco.util;

import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.VodDto;
import org.springframework.stereotype.Service;

@Service
public//public -> dafault로 변경(Entity를 직접 인풋으로 받는 거라 범위 줄임)
class VodtoVodDto {
    //static으로 만들어서 VodtoVodDto의 인스턴스 vodtoVodDto 만들지 않고
    //그냥 VodtoVodDto.vodtoVodDto 쓸 수 있게 변경
    //오로지 이 메서드만 쓸 수 있으면 되는 클래스라 인스턴스 필요없음
    public static VodDto vodtoVodDto(Vod vod) {
        return vod.toVodDto(vod);
    }
}
