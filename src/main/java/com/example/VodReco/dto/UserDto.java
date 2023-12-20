package com.example.VodReco.dto;


import com.example.VodReco.domain.user.User;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
