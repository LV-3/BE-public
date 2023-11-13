package com.example.VodReco.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Component // 확인 필요(231112)
public class ToModelDto {
    String modelName;
    List<?> responseData; //필드명이 헷갈림. 추후 수정(231113)
}
