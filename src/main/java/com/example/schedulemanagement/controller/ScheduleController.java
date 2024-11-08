package com.example.schedulemanagement.controller;


import com.example.schedulemanagement.dto.ScheduleJoinResponseDto;
import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.erros.errorcode.CustomErrorCode;
import com.example.schedulemanagement.erros.exception.RestApiException;
import com.example.schedulemanagement.service.ScheduleService;
import org.springframework.data.domain.Page;
import org.springframework.data.relational.core.sql.Assignment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto){

        return new ResponseEntity<>(scheduleService.saveSchedule(requestDto) , HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleJoinResponseDto>> findAllSchedule(
            @RequestParam(required = false) Long user_id,
            @RequestParam(required = false) Date date
    ){
        if (user_id != null && date == null){
            return new ResponseEntity<>(scheduleService.findAllScheduleByUserId(user_id) , HttpStatus.OK);
        } else if (user_id == null && date != null) {
            return new ResponseEntity<>(scheduleService.findAllScheduleByDate(date) , HttpStatus.OK);
        } else if (user_id != null && date != null) {
            return new ResponseEntity<>(scheduleService.findAllScheduleByUserIdAndDate(user_id, date) , HttpStatus.OK);
        }
        return new ResponseEntity<>(scheduleService.findAllSchedule(),HttpStatus.OK);
    }


    @GetMapping("/pages")
    public ResponseEntity<List<ScheduleJoinResponseDto>> findAllScheduleByPage(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize
    ){
        return new ResponseEntity<>(scheduleService.findAllScheduleByPage(pageNum,pageSize) , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById (@PathVariable Long id){
        return new ResponseEntity<>(scheduleService.findScheduleByIdOrElseThrow(id),HttpStatus.OK);
    }

    @GetMapping("/errors")
    public ResponseEntity<Schedule> getUser() {
        throw new RestApiException(CustomErrorCode.RESOURCE_NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto requestDto
    ){
        return new ResponseEntity<>(scheduleService.updateSchedule(id , requestDto.getPassword() , requestDto.getUser_id() , requestDto.getDetail()) , HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long id,
            @RequestParam String password
    ){
        scheduleService.deleteSchedule(id, password);

        return new ResponseEntity<>(HttpStatus.OK);
    }





}
