package com.mooninho.starbucks.v1.exceptionhandler;

import com.mooninho.starbucks.v1.exception.UserExceptionV1;
import com.mooninho.starbucks.v1.exception.code.UserErrorCodeV1;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandlerV1 {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResponseDTOV1 memberException(UserExceptionV1 e) {
        return new ErrorResponseDTOV1(UserErrorCodeV1.USER_ERROR, e.getMessage());
    }


}
