package com.example.VodReco.dto.Recommendation.model.fromModel.receivedContentIds;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class ReceivedDescriptionContentIds {
    private List<String> receivedDescriptionDataList = new ArrayList<>();
}
