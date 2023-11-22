package com.example.VodReco.util;

import java.util.List;

public class ListToStringConverter {

    //프로젝트 내에서 사용하던 VodDto를 Vod엔티티(=DB와 직접 연결되는 것)으로 변환할 때
    //List<String>에서 다시 String 하나로 join하는 메서드 생성(231123)
    //static으로 선언해서 인스턴스 생성하지 않아도 바로 사용 가능
    //구분자는 (, )
    public static String listToString(List<String> list) {
        return String.join(", ", list);
    }

}
