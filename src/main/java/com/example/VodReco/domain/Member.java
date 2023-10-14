package com.example.VodReco.domain;

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
    private Integer genre1;
    @Column
    private Integer genre2;
    @Column
    private Integer genre3;

    @Builder
    public Member(Integer id, String email, String password, Integer genre1,Integer genre2, Integer genre3) {
        this.id = id;
        this.email = email;
//        this.username = username;
        this.password = password;
        this.genre1 = genre1;
        this.genre2 = genre2;
        this.genre3 = genre3;
    }
}