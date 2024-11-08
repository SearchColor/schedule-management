package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.ScheduleJoinResponseDto;
import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;

import java.sql.Date;
import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto);

    List<ScheduleJoinResponseDto> findAllSchedule();

    List<ScheduleJoinResponseDto> findAllScheduleByUserId(Long user_id);

    List<ScheduleJoinResponseDto> findAllScheduleByDate(Date date);

    List<ScheduleJoinResponseDto> findAllScheduleByUserIdAndDate(Long user_id , Date date);

    ScheduleResponseDto findScheduleByIdOrElseThrow (Long id);

    ScheduleResponseDto updateSchedule(Long id , String password , String detail);

    void deleteSchedule(Long id , String password);

    List<ScheduleJoinResponseDto> findAllScheduleByPage(Integer pageNum ,Integer pageSize);
}
