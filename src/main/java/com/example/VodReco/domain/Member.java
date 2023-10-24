package com.example.VodReco.domain;

import jakarta.persistence.*;
import lombok.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
//@NoArgsConstructor 기본생성자 직접 작성함
public class Member {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
    @Id
    @Column(nullable = false, unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private List<String> selectedVods;
//    @Column
//    private Integer genre1;
//    @Column
//    private Integer genre2;
//    @Column
//    private Integer genre3;

     public Member(){
    }


     @Builder
    public Member(String email, String password, List<String> selectedVods) {
        this.email = email;
        this.password = password;
        this.selectedVods = selectedVods;
    }

}





