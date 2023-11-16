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
    private String subsr;

    @Builder
    public UserDto(String subsr) {
        this.subsr = subsr;
    }


    public static UserDto from(User user) {
        if(user == null) return null;

        return UserDto.builder().subsr(user.getSubsr()).build();
    }
}
