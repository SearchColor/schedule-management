package com.example.schedulemanagement.dto;


import com.example.schedulemanagement.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private String password;
    private Long user_id;
    private String detail;
    private Date registration_date;
    private Date modification_date;

    public ScheduleResponseDto(Schedule schedule){
        this.id = schedule.getId();
        this.password = schedule.getPassword();
        this.user_id = schedule.getUser_id();
        this.detail = schedule.getDetail();
        this.registration_date = schedule.getRegistration_date();
        this.modification_date = schedule.getModification_date();

    }

}
