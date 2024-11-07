package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedule();

    List<ScheduleResponseDto> findAllScheduleByUserId(Long user_id);

    int updateSchedule(Long id , String password , Long user_id , String detail);

    int deleteSchedule(Long id , String password);


}
