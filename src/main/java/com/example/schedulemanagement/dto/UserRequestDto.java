package com.example.schedulemanagement.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @Email
    private String email;

    @NotEmpty (message = "필수 입력 요소 입니다.")
    private String name;

}
