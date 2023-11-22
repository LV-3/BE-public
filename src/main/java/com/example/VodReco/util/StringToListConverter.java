package com.example.VodReco.util;

import java.util.Arrays;
import java.util.List;

public class StringToListConverter {
    //DB로부터 꺼내온 String을 List<String>으로 변환하는 메서드 생성
    //static으로 선언해서 인스턴스 생성하지 않아도 바로 사용 가능
    //구분자는 (, )
    public static List<String> stringToList(String str) {
        String[] splitedStr = str.split(", ");
        return Arrays.stream(splitedStr).toList();
    }

}
