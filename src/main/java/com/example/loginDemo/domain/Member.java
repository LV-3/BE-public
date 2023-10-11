package com.example.loginDemo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @Column
//    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private String genre1;
    @Column
    private String genre2;
    @Column
    private String genre3;

    @Builder
    public Member(Integer id, String email, String password, String genre1, String genre2, String genre3) {
        this.id = id;
        this.email = email;
//        this.username = username;
        this.password = password;
        this.genre1 = genre1;
        this.genre2 = genre2;
        this.genre3 = genre3;
    }
}