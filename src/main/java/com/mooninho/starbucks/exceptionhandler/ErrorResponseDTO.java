package com.mooninho.starbucks.exceptionhandler;

import com.mooninho.starbucks.exception.code.UserErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseDTO {

    private UserErrorCode code;
    private String message;
}
