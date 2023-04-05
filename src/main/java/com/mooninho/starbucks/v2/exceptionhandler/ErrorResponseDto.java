package com.mooninho.starbucks.v2.exceptionhandler;

import com.mooninho.starbucks.v2.exception.code.UserErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseDto {

    private UserErrorCode code;
    private String message;
}
