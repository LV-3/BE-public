package com.example.VodReco.dto;


import com.example.VodReco.domain.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
//@Builder
//@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private String password;

    @NotNull
    private String nickname;

    private String gender;
    private String birthYear;

    private List<String> selectedVods;

    private Set<AuthorityDto> authorityDtoSet;

    @Builder
    public UserDto(String email, String password, String nickname, String gender, String birthYear, List<String> selectedVods, Set<AuthorityDto> authorityDtoSet) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.birthYear = birthYear;
        this.selectedVods = selectedVods;
        this.authorityDtoSet = authorityDtoSet;
    }


    public static UserDto from(User user) {
        if(user == null) return null;

        return UserDto.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .gender(user.getGender())
                .birthYear(user.getBirthYear())
                .selectedVods(user.getSelectedVods())
                .authorityDtoSet(user.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
