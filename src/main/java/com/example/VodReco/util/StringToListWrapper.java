package com.example.VodReco.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class StringToListWrapper {
    //DB로부터 꺼내온 String을 List<String>으로 변환하는 메서드 생성
    //static으로 선언해서 인스턴스 생성하지 않아도 바로 사용 가능
    //구분자는 (, )


    //아예 스프링 빈에 등록해서 autowired해서 인스턴스 싱글톤으로 생성되게 바꾸기
    //static으로 쓰면 1. 메모리 낭비 2. 퍼블릭으로 선언할 수밖에 없으니까 아무나 갖다 쓸 수 있음. static 지양하기


    public List<String> stringToList(String str) {
        String[] splitedStr = str.split(", ");
        return Arrays.stream(splitedStr).toList();
    }

}
