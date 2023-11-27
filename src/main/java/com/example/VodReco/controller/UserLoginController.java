package com.example.VodReco.controller;

import com.example.VodReco.dto.login.LoginDto;
import com.example.VodReco.service.login.UserLoginServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserLoginController {
    private final UserLoginServiceImpl userLoginService;

    //셋탑아이디 로그인
    // [재아] 로그인 성공, 실패 나눠서 구현
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        String loginResult = userLoginService.ValidateUser(loginDto);
        if (loginResult.equals("success")) {
            return ResponseEntity.ok().body("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }

}
