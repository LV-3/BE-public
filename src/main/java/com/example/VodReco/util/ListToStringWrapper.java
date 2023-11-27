package com.example.VodReco.util;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListToStringWrapper {

    //프로젝트 내에서 사용하던 VodDto를 Vod엔티티(=DB와 직접 연결되는 것)으로 변환할 때
    //List<String>에서 다시 String 하나로 join하는 메서드 생성(231123)
    //static으로 선언해서 인스턴스 생성하지 않아도 바로 사용 가능
    //구분자는 (, )
    public String listToString(List<String> list) {
        return convertListToString(list);
    }

    private String convertListToString(List<String> list)  {
        if (list == null) {
            return null;
        }else {
            return String.join(", ", list);
        }
    }

}
