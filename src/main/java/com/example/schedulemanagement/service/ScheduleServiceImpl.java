package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {

        Schedule schedule = new Schedule(requestDto.getPassword() , requestDto.getUser_id() , requestDto.getDetail() );

        ScheduleResponseDto scheduleResponseDto = scheduleRepository.saveSchedule(schedule);

        return scheduleResponseDto;
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule() {

        List<ScheduleResponseDto> allSchedule = scheduleRepository.findAllSchedule();
        return allSchedule;
    }

    @Override
    public List<ScheduleResponseDto> findAllScheduleByUserId(Long user_id) {
        List<ScheduleResponseDto> allScheduleByUserId = scheduleRepository.findAllScheduleByUserId(user_id);
        return allScheduleByUserId;
    }
}
