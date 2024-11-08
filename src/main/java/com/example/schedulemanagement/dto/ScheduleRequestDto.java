package com.example.schedulemanagement.dto;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;


@Getter
public class ScheduleRequestDto {

    @NotEmpty(message = "password 는 필수 입력값입니다.")
    private String password;

    private Long user_id;

    @Size(max = 200 , message = "200자 이내로 작성해야합니다.")
    private String detail;
}
