package com.example.VodReco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultLandingPageController {

    @GetMapping("")
    public String root() {
        return "layout";
    }
}
