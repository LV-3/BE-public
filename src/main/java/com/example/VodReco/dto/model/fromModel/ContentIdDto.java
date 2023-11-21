package com.example.VodReco.dto.model.fromModel;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ContentIdDto {
    private List<String> content_id;

}
