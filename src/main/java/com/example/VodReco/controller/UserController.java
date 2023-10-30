package com.example.VodReco.controller;

import com.example.VodReco.dto.JoinRequestDto;
import com.example.VodReco.dto.UserDto;
import com.example.VodReco.dto.ValidateEmailDto;
import com.example.VodReco.dto.ValidateNicknameDto;
import com.example.VodReco.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/signup")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @PostMapping("/test-redirect")
    public void testRedirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/api/user");
    }

    //이메일 중복검사
    @PostMapping("/email-dcheck")
    public Boolean validateDuplicateEmail(@RequestBody ValidateEmailDto validateEmailDto){
        return userService.validateDuplicateEmail(validateEmailDto);
//        return ResponseEntity.ok(userService.signup(userDto));
    }

    //닉네임 중복검사
    @PostMapping("/name-dcheck")
    public Boolean validateDuplicateNickname(@RequestBody ValidateNicknameDto validateNicknameDto){
        return userService.validateDuplicateNickname(validateNicknameDto);
//        return ResponseEntity.ok(userService.signup(userDto));
    }


    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(username));
    }

    @GetMapping("/user")
    public
    @PreAuthorize("hasAnyRole('USER','ADMIN')") ResponseEntity<UserDto> getMyUserInfo(HttpServletRequest request) {

        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }

    //중복검사 2회 통과한 유저 한정 회원가입
    //signup 안에는 중복검사 로직 없는 이유: 어차피 프론트에서 중복검사 끝내야 회원가입 버튼 활성화되게 만들 것
    @PostMapping("/sign")
    //localhost:8080/signup으로 PostMapping으로 바꿀 방안 찾기
    public ResponseEntity<UserDto> signUp(@RequestBody UserDto userDto) {
        //UserDto 안에 있는 authority가 빈 채로 데이터 들어와도 에러 안 나나? (231030)
        //-> 안 남. 안 들어온 필드는 알아서 null로 처리하고 + 이 경우 authorityDtoSet은 service단에서 직접 만들어서 주입해줌
        //@RequestBody의 매핑은 Builder패턴 사용 시에는 필드 명에 정확히 따라가고 빌더 안 쓰면 아닌 것으로 생각됨
        //다시 찾아보기(231030)
        return ResponseEntity.ok(userService.signup(userDto));
    }

}


