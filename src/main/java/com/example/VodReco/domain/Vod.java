package com.example.VodReco.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "vods") //DB에 존재하는 테이블과 매핑해서 데이터 가져오는 어노테이션
                        //DB의 컬럼명과 정확히 같게 설정해야 함
@Getter
//@NoArgsConstructor 기본 생성자 직접 작성
public class Vod {
    @Id
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, unique = true)
    private String vcode;
    private String country;
    private String genre;
    private String director;
    private String posterurl;

    public Vod(){
    }

    @Builder
    public Vod(String title, String vcode, String country, String genre, String director, String posterurl) {
        this.title = title;
        this.vcode = vcode;
        this.country = country;
        this.genre = genre;
        this.director = director;
        this.posterurl = posterurl;
    }
}
