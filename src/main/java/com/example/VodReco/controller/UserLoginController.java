package com.example.VodReco.controller;

import com.example.VodReco.dto.login.LoginDto;
import com.example.VodReco.service.login.UserLoginServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserLoginController {
    private final UserLoginServiceImpl userLoginService;

    //셋탑아이디 로그인
    // [재아] 로그인 성공, 실패 나눠서 구현
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        //ValidateUser의 리턴에 맞게 equals 내부 값 수정(231128)
        //요구사항에 맞게 리턴 null로 수정(231128)
        if (userLoginService.ValidateUser(loginDto).equals(loginDto.getSubsr())) {
            return ResponseEntity.ok().body(userLoginService.ValidateUser(loginDto));
        } else {
            //에러코드 401(UnAuthorized)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

}
