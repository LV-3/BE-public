package com.example.VodReco.dto.model;

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
    private List<EveryDescription> description_data;
    //두 클래스 사이에 전역변수 List하나 껴서 전달해줘야 할듯
}
