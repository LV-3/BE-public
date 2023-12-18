package com.example.VodReco.dto.Recommendation.model.fromModel.receivedContentIds;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class ReceivedPersonalContentIds {
    private List<String> receivedPersonalDataList = new ArrayList<>();

}
