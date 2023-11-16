package com.example.VodReco.controller;

import com.example.VodReco.dto.LoginDto;
import com.example.VodReco.service.UserLoginServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserLoginController {
    private final UserLoginServiceImpl userServieImpl;

    @GetMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        return userServieImpl.ValidateUser(loginDto);
    }
}
