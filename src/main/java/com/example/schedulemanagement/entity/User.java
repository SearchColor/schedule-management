package com.example.schedulemanagement.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

    private Long id;
    private String email;
    private String name;

    public User(String email , String name , String registration_date , String modification_date){
        this.email = email;
        this.name = name;
    }



}
