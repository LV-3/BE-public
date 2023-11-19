package com.example.VodReco.service.login;

import com.example.VodReco.dto.login.LoginDto;
import org.springframework.stereotype.Service;

@Service
public interface UserLoginService {
    String ValidateUser(LoginDto loginDto);
}
