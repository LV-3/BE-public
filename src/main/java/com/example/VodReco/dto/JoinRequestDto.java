package com.example.VodReco.dto;

import com.example.VodReco.domain.Member;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@Getter
//@ToString
@Setter
@NoArgsConstructor
public class JoinRequestDto {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
    @Id
    private String email;

    private String password;

    private List<String> selectedVods;

//    private Integer genre1;
//
//    private Integer genre2;
//    private Integer genre3;

//    public JoinRequestDto(){
//        genre1 = 0;
//        genre2 = 0;
//        genre3 = 0;
//    }

    public Member toMemberEntity(JoinRequestDto joinRequestDto){
        return Member.builder()
//                .id(joinRequestDto.getId())
                .email(joinRequestDto.getEmail())
                .password(joinRequestDto.getPassword())
                .selectedVods(joinRequestDto.selectedVods)

//                .genre1(joinRequestDto.getGenre1())
//                .genre2(joinRequestDto.getGenre2())
//                .genre3(joinRequestDto.getGenre3())
                .build();
    }
    @Builder
    public JoinRequestDto(String email, String password, List<String> selectedVods){
//        this.id = id;
        this.email = email;
        this.password = password;
        this.selectedVods = selectedVods;
//        this.genre1 = genre1;
//        this.genre2 = genre2;
//        this.genre3 = genre3;
    }


}
