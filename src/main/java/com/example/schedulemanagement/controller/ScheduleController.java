package com.example.schedulemanagement.controller;


import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.service.ScheduleService;
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
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedule(
            @RequestParam(required = false) Long user_id,
            @RequestParam(required = false) Date modification_date
    ){
        if (user_id != null && modification_date == null){
            return new ResponseEntity<>(scheduleService.findAllScheduleByUserId(user_id) , HttpStatus.OK);
        } else if (user_id == null && modification_date != null) {
            return new ResponseEntity<>(scheduleService.findAllScheduleByDate(modification_date) , HttpStatus.OK);
        } else if (user_id != null && modification_date != null) {
            return new ResponseEntity<>(scheduleService.findAllScheduleByUserIdAndDate(user_id, modification_date) , HttpStatus.OK);
        }

        return new ResponseEntity<>(scheduleService.findAllSchedule(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById (@PathVariable Long id){
        return new ResponseEntity<>(scheduleService.findScheduleByIdOrElseThrow(id),HttpStatus.OK);
    }



}
