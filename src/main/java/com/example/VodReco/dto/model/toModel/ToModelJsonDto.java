package com.example.VodReco.dto.model.toModel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class ToModelJsonDto {
    private String jsonDto;
}
