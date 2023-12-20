package com.example.VodReco.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Document(collection = "user_id")
@Getter
//@AllArgsConstructor // Accesslevel 좁히는 방법 있음, 목적이 있다면 추가 가능(231102)
@NoArgsConstructor
//@ToString // 필요성 생기면 활성화(231102)
public class User {
//    @Id
    @Column(name = "subsr", nullable = false)
    private String subsr;

}