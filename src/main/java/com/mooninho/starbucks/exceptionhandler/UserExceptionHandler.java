package com.mooninho.starbucks.exceptionhandler;

import com.mooninho.starbucks.exception.UserException;
import com.mooninho.starbucks.exception.code.UserErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResponseDTO memberException(UserException e) {
        return new ErrorResponseDTO(UserErrorCode.LOGIN_ERROR, e.getMessage());
    }


}
