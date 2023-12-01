package com.example.VodReco.dto.model.fromModel.receivedContentIds;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
@Setter
public class ReceivedMoodContentIds {
    private List<String> receivedMoodDataList = new ArrayList<>();

}
