package com.example.schedulemanagement.dto;


import com.example.schedulemanagement.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private Long id;
    private String email;
    private String name;


    public UserResponseDto(User user){

        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();

    }
}
