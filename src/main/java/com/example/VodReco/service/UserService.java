package com.example.VodReco.service;

import com.example.VodReco.dto.LoginDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    String ValidateUser(LoginDto loginDto);
}
