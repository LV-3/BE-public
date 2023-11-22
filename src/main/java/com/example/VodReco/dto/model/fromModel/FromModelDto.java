package com.example.VodReco.dto.model.fromModel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FromModelDto {
    private ContentIdDto description_data;
    private ContentIdDto mood_data;
    private ContentIdDto personal_data;

}
