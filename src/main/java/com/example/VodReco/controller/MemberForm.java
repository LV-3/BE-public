package com.example.VodReco.controller;

import lombok.Getter;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Getter
public class MemberForm {

    private String email;
    private String password;
    private List<String> selectedVods;

}
