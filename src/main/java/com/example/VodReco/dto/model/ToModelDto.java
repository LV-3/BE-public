package com.example.VodReco.dto.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Component // 확인 필요(231112)
public class ToModelDto {
    private String modelName;
    private List<?> responseData; //필드명이 헷갈림. 추후 수정(231113)
}
