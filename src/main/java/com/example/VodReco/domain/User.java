package com.example.VodReco.domain;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "subsr", nullable = false)
    private String subsr;

    @Builder
    public User(String subsr) {
        this.subsr = subsr;
    }
}