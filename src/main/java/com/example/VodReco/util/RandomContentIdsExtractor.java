package com.example.VodReco.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class RandomContentIdsExtractor {
    public List<String> extractFiveRandContentIds(List<String> contentIds) {
        return extractRandomElements(contentIds);
    }

    private List<String> extractRandomElements(List<String> list) {
        Random rand = new Random();
        int totalElements = list.size();
        List<String> randomContentIdsList = new ArrayList<>();

        //랜덤으로 5개 뽑는데 전체 요소 개수가 5보다 작거나 같을 경우
        //그대로 리턴(랜덤 추출 필요 없음)
        if (5 >= totalElements) {
            return list;
        }
        //랜덤추출
        //randomConetentIdsList.size()가 5 되면 리턴
        while (randomContentIdsList.size() < 5) {
            int randIndex = rand.nextInt(totalElements);
            String randomContentId = list.get(randIndex);
            //중복 검사
            if (!randomContentIdsList.contains(randomContentId)) {
                randomContentIdsList.add(randomContentId);
            }
        }
        return randomContentIdsList;
    }

}
