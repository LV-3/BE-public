package com.example.VodReco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {    //메인페이지
    @GetMapping("/")//그냥 localhost:8080 들어오면 아래 호출해라
    public String home() {
        return "home";
    }
}
