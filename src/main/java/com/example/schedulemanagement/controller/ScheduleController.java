package com.example.schedulemanagement.controller;


import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedule(){
        return new ResponseEntity<>(scheduleService.findAllSchedule(),HttpStatus.OK);
    }

    @GetMapping("/user-id/{user_id}")
    public ResponseEntity<List<ScheduleResponseDto>> findAllScheduleByUserId(@PathVariable Long user_id){
        return new ResponseEntity<>(scheduleService.findAllScheduleByUserId(user_id) , HttpStatus.OK);
    }

}
