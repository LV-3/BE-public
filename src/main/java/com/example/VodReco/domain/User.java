package com.example.VodReco.domain;

import com.example.VodReco.service.StringAttributeConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
//@AllArgsConstructor // Accesslevel 좁히는 방법 있음, 목적이 있다면 추가 가능(231102)
@NoArgsConstructor
//@ToString // 필요성 생기면 활성화(231102)
public class User {
    @Id
    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "gender")
    private String gender;

    //변수명 합의 되면 수정(231031)
    @Column(name = "birth_year")
    private String birthYear;

    @Column
    @Convert(converter = StringAttributeConverter.class)
    private List<String> selectedVods;

    @Column(name = "activated")
    private boolean activated;

    @ManyToMany
    @JoinTable( // 여기서 FK 지정되는듯
            //DB에 미리 저장해둔 user_authority에서 꺼내오는 것
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "email", referencedColumnName = "email")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;



    @Builder // User, UserDto @Builder 어노테이션 클래스 말고 메서드로 구현하기
    public User(String email, String password, String nickname, String gender, String birthYear, List<String> selectedVods, boolean activated, Set<Authority> authorities) {
       this.email = email;
       this.password = password;
       this.nickname = nickname;
       this.gender = gender;
       this.birthYear = birthYear;
       this.selectedVods = selectedVods;
       this.activated = activated;
       this.authorities = authorities;
    }


}