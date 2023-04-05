package com.mooninho.starbucks.v2.exceptionhandler;

import com.mooninho.starbucks.v2.exception.UserException;
import com.mooninho.starbucks.v2.exception.code.UserErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserException.class)
    public ErrorResponseDto memberException(UserException e) {
        return new ErrorResponseDto(UserErrorCode.USER_ERROR, e.getMessage());
    }
}
