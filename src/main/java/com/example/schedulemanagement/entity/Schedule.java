package com.example.schedulemanagement.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id;
    private String password;
    private Long user_id;
    private String detail;
    private LocalDate registration_date;
    private LocalDate modification_date;


    public Schedule(String password , Long user_id , String detail){
        this.password = password;
        this.user_id = user_id;
        this.detail = detail;
    }

//    public Schedule(String password , Long user_id ,String detail , String registration_date , String modification_date){
//        this.password = password;
//        this.user_id = user_id;
//        this.detail = detail;
//        this.registration_date = registration_date;
//        this.modification_date = modification_date;
//    }

}
