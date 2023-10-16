package com.example.VodReco.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CloseVodDetail {
    private boolean wish;
    private Integer rating;

    //boolean타입은 getter가 is__ 이름으로 만들어짐
    public boolean getWish() {
        return this.wish;
    }
}
