package com.example.VodReco.dto.model.toModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor

//변수명 확인받기. 필요한 이름으로 보내줄 수 있음(231126)
//genre, director는 List<String>으로 보내야 하는지?
public class EveryPersonal {
    @JsonProperty("content_id")
    private String contentId;
    private List<String> mood;
    private List<String> genre;
    private

}
