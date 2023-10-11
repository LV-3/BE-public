package com.example.loginDemo.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@Getter
@Setter
public class MemberForm {
    private String email;
    private String username;
    private String password;
}
