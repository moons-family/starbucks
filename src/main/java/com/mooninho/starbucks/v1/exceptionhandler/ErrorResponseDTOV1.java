package com.mooninho.starbucks.v1.exceptionhandler;

import com.mooninho.starbucks.v1.exception.code.UserErrorCodeV1;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseDTOV1 {

    private UserErrorCodeV1 code;
    private String message;
}
