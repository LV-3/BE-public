package com.example.loginDemo.dto;

import com.example.loginDemo.domain.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.boot.autoconfigure.mail.MailProperties;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class JoinRequestDto {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //    @Column(nullable = false, unique = true)
//    private String username;

    private String email;


    private String password;

    private String genre1;
    private String genre2;
    private String genre3;



    public Member toMemberEntity(){
        Member build = Member.builder()
                .id(id)
//                .username(username)
                .email(email)
                .password(password)
                .genre1(genre1)
                .genre2(genre2)
                .genre3(genre3)
                .build();
        return build;
    }
    @Builder
    public JoinRequestDto(Integer id, String email,String password, String genre1, String genre2, String genre3){
        this.id = id;
        this.email = email;
//        this.username = username;
        this.password = password;
        this.genre1 = genre1;
        this.genre2 = genre2;
        this.genre3 = genre3;
    }


}
