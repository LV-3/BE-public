package com.example.VodReco.dto.model.toModel;

import com.example.VodReco.dto.client.ToClient1stDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class ToClientListDto {
    private List<ToClient1stDto> descriptionListToClient = new ArrayList<>();
    private List<ToClient1stDto> moodListToClient = new ArrayList<>();
    private List<ToClient1stDto> personalListToClient = new ArrayList<>();
    private String personal_words = "";
}