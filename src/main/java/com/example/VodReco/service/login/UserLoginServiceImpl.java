package com.example.VodReco.service.login;

import com.example.VodReco.dto.login.LoginDto;
import com.example.VodReco.mongoRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {
    private final UserRepository userRepository;
    @Override
    public String ValidateUser(LoginDto loginDto) {
        String subsr = loginDto.getSubsr();
        if (userRepository.findBySubsr(subsr) == null) {
            return null;
        }else {
            return loginDto.getSubsr();
        }
    }
}
