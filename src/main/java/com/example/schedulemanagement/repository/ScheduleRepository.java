package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.ScheduleJoinResponseDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;

import java.sql.Date;
import java.util.List;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleJoinResponseDto> findAllSchedule();

    List<ScheduleJoinResponseDto> findAllScheduleByUserId(Long user_id);

    List<ScheduleJoinResponseDto> findAllScheduleByDate(Date date);

    List<ScheduleJoinResponseDto> findAllScheduleByUserIdAndDate(Long user_id , Date date);

    Schedule findScheduleByIdOrElseThrow(Long id);

    int updateSchedule(Long id , String password , String detail);

    int deleteSchedule(Long id , String password);

    List<ScheduleJoinResponseDto> findAllScheduleByPage(Integer pageNum , Integer pageSize);


    int countScheduleById(Long id);

}
