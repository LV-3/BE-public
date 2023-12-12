package com.example.VodReco.dto.model.toModel;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class ToModel2ndDto {
    private ToModelDto dataForModel;

    @Builder
    public ToModel2ndDto(ToModelDto dataForModel) {
        this.dataForModel = dataForModel;
    }
}
