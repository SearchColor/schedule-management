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
    private Date registration_date;
    private Date modification_date;


    public Schedule(String password , Long user_id , String detail){
        this.password = password;
        this.user_id = user_id;
        this.detail = detail;
    }


}
