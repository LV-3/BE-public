package com.example.VodReco.dto.model.toModel;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@NoArgsConstructor
@Component
@Setter
public class ToDescriptionModelDto {
    private String modelName;
    private List<EveryDescription> responseData;

    @Builder
    public ToDescriptionModelDto(String modelName, List<EveryDescription> responseData) {
        this.modelName = modelName;
        this.responseData = responseData;
    }
}
