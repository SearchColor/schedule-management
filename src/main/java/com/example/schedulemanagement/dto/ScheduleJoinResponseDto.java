package com.example.schedulemanagement.dto;

import com.example.schedulemanagement.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;


@Getter
@AllArgsConstructor
public class ScheduleJoinResponseDto {

    private Long id;
    private String password;
    private Long user_id;
    private String user_name;
    private String detail;
    private Date registration_date;
    private Date modification_date;

}
