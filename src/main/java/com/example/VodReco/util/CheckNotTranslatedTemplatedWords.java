package com.example.VodReco.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class CheckNotTranslatedTemplatedWords {
    public boolean checkIfNotTranslated(String templateWord) {
        return ifNotTranslated(templateWord);
    }
    //번역 여부 검사 메서드
    //알파벳으로 이루어진 경우 true 리턴
    private boolean ifNotTranslated(String templateWord) {
        return Pattern.matches("^[a-zA-Z]*$", templateWord);
    }

}
