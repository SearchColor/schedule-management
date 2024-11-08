package com.example.schedulemanagement.erros.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode implements ErrorCode {

    //parameter 부족 에러코드
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),

    //password 실패시 에러코드
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "Invalid password"),

    //id 조회 실패시 에러코드
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Cannot find resource."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

}