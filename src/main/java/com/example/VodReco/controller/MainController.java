package com.example.VodReco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {    //메인페이지
    //현재 리턴타입 void인 빈 메서드로 바꾸면 thymeleaf에러 -> thymeleaf 의존성 제거 후 재시도(231019)
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
