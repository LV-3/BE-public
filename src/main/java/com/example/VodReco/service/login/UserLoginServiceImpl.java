package com.example.VodReco.service.login;

import com.example.VodReco.dto.login.LoginDto;
import com.example.VodReco.mongoRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserLoginServiceImpl implements UserLoginService {
    private final UserRepository userRepository;
    @Override
    public String ValidateUser(LoginDto loginDto) {
        String subsr = loginDto.getSubsr();
        //userRepository.findBySubsr(subsr)의 리턴타입은 List. ==null이 아닌 .isEmpty() 사용해야 함(231128)
        if (userRepository.findBySubsr(subsr).isEmpty()) {
            return "Login Failed";
        }else {
            return loginDto.getSubsr();
        }
    }
}
