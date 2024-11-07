package com.example.schedulemanagement.dto;


import com.example.schedulemanagement.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private String password;
    private Long user_id;
    private String detail;
    private LocalDate registration_date;
    private LocalDate modification_date;

    public ScheduleResponseDto(Schedule schedule){
        this.id = schedule.getId();
        this.password = schedule.getPassword();
        this.user_id = schedule.getUser_id();
        this.detail = schedule.getDetail();
        this.registration_date = schedule.getRegistration_date();
        this.modification_date = schedule.getModification_date();

    }

//    public ScheduleResponseDto(Long id ,String password , Long user_id , String detail){
//        this.id = id;
//        this.password = password;
//        this.user_id =user_id;
//        this.detail = detail;
//
//    }

}
