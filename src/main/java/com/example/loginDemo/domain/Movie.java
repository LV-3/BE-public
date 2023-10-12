package com.example.loginDemo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "movies") //DB에 존재하는 테이블과 매핑해서 데이터 가져오는 어노테이션
                        //DB의 컬럼명과 정확히 같게 설정해야 함
@Getter
@Setter
@NoArgsConstructor
public class Movie {
    @Id
    private String title;
    private String mcode;
    private String country;
    private String genre;
    private String director;
    private String posterurl;

}
