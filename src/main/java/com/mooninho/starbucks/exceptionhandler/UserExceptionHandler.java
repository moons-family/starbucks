package com.mooninho.starbucks.exceptionhandler;

import com.mooninho.starbucks.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResponseDTO userExHandler(UserException e) {
        return new ErrorResponseDTO("USER-EX", e.getMessage());
    }
}
