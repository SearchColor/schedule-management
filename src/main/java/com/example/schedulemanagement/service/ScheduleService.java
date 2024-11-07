package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;

import java.sql.Date;
import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto);

    List<ScheduleResponseDto> findAllSchedule();

    List<ScheduleResponseDto> findAllScheduleByUserId(Long user_id);

    List<ScheduleResponseDto> findAllScheduleByDate(Date date);

    List<ScheduleResponseDto> findAllScheduleByUserIdAndDate(Long user_id , Date date);

    ScheduleResponseDto findScheduleByIdOrElseThrow (Long id);


}
