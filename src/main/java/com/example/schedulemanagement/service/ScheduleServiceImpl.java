package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.ScheduleJoinResponseDto;
import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.erros.errorcode.CustomErrorCode;
import com.example.schedulemanagement.erros.exception.RestApiException;
import com.example.schedulemanagement.repository.ScheduleRepository;
import org.hibernate.validator.internal.constraintvalidators.bv.notempty.NotEmptyValidatorForArray;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.EmptyStackException;
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
    public List<ScheduleJoinResponseDto> findAllSchedule() {

        List<ScheduleJoinResponseDto> allSchedule = scheduleRepository.findAllSchedule();
        return allSchedule;
    }

    @Override
    public List<ScheduleJoinResponseDto> findAllScheduleByPage(Integer pageNum, Integer pageSize) {

        List<ScheduleJoinResponseDto> findAllScheduleByPage = scheduleRepository.findAllScheduleByPage(pageNum , pageSize);
        return findAllScheduleByPage;
    }


    @Override
    public List<ScheduleJoinResponseDto> findAllScheduleByUserId(Long user_id) {
        List<ScheduleJoinResponseDto> allScheduleByUserId = scheduleRepository.findAllScheduleByUserId(user_id);
        return allScheduleByUserId;
    }

    @Override
    public List<ScheduleJoinResponseDto> findAllScheduleByDate(Date date) {
        List<ScheduleJoinResponseDto> allScheduleByDate = scheduleRepository.findAllScheduleByDate(date);
        return allScheduleByDate;
    }

    @Override
    public List<ScheduleJoinResponseDto> findAllScheduleByUserIdAndDate(Long user_id, Date date) {
        List<ScheduleJoinResponseDto> allScheduleByUserIdAndDate = scheduleRepository.findAllScheduleByUserIdAndDate(user_id , date);
        return allScheduleByUserIdAndDate;
    }

    @Override
    public ScheduleResponseDto findScheduleByIdOrElseThrow(Long id) {

        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long id, String password, String detail) {

        if(password == null || detail == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "The password and detail are required values.");
        }

        int slectSchedule = scheduleRepository.countScheduleById(id);

        if (slectSchedule == 0){
            throw new RestApiException(CustomErrorCode.RESOURCE_NOT_FOUND);
        }

        int updateRow = scheduleRepository.updateSchedule(id,password,detail);

        if (updateRow == 0){
            throw new RestApiException(CustomErrorCode.INVALID_PASSWORD);
        }

        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        return new ScheduleResponseDto(schedule);
    }

    @Override
    public void deleteSchedule(Long id , String password) {

        int slectSchedule = scheduleRepository.countScheduleById(id);

        if (slectSchedule == 0){
            throw new RestApiException(CustomErrorCode.RESOURCE_NOT_FOUND);
        }
        int deleteRow = scheduleRepository.deleteSchedule(id ,password);

        if (deleteRow == 0){
            throw new RestApiException(CustomErrorCode.INVALID_PASSWORD);
        }

    }



}
