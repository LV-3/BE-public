package com.example.VodReco.dto;

import com.example.VodReco.domain.Member;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginRequestDto {
    private Integer id;
    private String email;
    private String username;
    private String password;

    public Member toMemberEntity() {
        Member build = Member.builder()
                .email(email)
//                .username(username)
                .password(password)
//                .genre1(genre1) ??
                .build();
        return build;
    }

    @Builder
    public LoginRequestDto(Integer id, String email, String username, String password) {
    }
}
