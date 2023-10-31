package com.example.VodReco.service;


import com.example.VodReco.domain.Authority;
import com.example.VodReco.domain.User;
import com.example.VodReco.dto.UserDto;
import com.example.VodReco.dto.ValidateEmailDto;
import com.example.VodReco.dto.ValidateNicknameDto;
import com.example.VodReco.exception.DuplicateMemberException;
import com.example.VodReco.exception.NotFoundMemberException;
import com.example.VodReco.repository.UserRepository;
import com.example.VodReco.util.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public Boolean validateDuplicateEmail(ValidateEmailDto validateEmailDto) {
        if (userRepository.findOneWithAuthoritiesByEmail(validateEmailDto.getEmail()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 이메일입니다.");
        }
        return true;
    }

    @Transactional
    public Boolean validateDuplicateNickname(ValidateNicknameDto validateNicknameDto) {
        if (userRepository.findOneWithAuthoritiesByNickname(validateNicknameDto.getNickname()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 존재하는 닉네임입니다.");
        }
        return true;
    }

//    @Transactional
//    public UserDto signup(@RequestBody UserDto userDto) {
//        //의문점: 들어오는 정보에는 authority데이터는 없는데 알아서 UserDto에 매핑해줌?
//        //바로 및 코드가 authority 생성해주는 건데 그런 의도인건가??(231030)
    @Transactional
    public UserDto signup(UserDto userDto) {

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        //실제 프론트와 연결할 때는 true만 보내거나 void로 저장만 하면 됨
    // API 테스트용 리턴

        //새로 안 사실: jpa의 save는 사실 save된 entity 반환함
        //지금까지 리턴값이 있단 사실도 몰라서 어디다 담아서 받지도 않아봄ㅎ
        //따라서 from의 input으로 user가 들어감
        return UserDto.from(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public UserDto getUserWithAuthorities(String email) {
        return UserDto.from(userRepository.findOneWithAuthoritiesByEmail(email).orElse(null));
    }

    @Transactional(readOnly = true)
    public UserDto getMyUserWithAuthorities() {
        return UserDto.from(
                SecurityUtil.getCurrentUsername()
                        .flatMap(userRepository::findOneWithAuthoritiesByEmail)
                        .orElseThrow(() -> new NotFoundMemberException("Member not found"))
        );
    }
}
