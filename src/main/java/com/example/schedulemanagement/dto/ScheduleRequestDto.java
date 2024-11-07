package com.example.schedulemanagement.dto;


import com.example.schedulemanagement.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    private String password;
    private Long user_id;
    private String detail;
}
