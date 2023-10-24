package com.example.VodReco.service;

import com.example.VodReco.domain.Member;
import com.example.VodReco.dto.JoinRequestDto;
import com.example.VodReco.dto.LoginRequestDto;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    void join(JoinRequestDto joinRequestDto);

    boolean validateGenres(Optional<Integer>[] optionals);

//    LoginRequestDto login(LoginRequestDto loginRequestDto);

    List<Member> findMembers();

    Optional<Member> findOne(String email);
}
