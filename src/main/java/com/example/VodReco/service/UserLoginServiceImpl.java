package com.example.VodReco.service;

import com.example.VodReco.dto.LoginDto;
import com.example.VodReco.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserService{
    private UserRepository userRepository;
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
