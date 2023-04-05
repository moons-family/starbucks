package com.mooninho.starbucks.v1.exception;

public class UserExceptionV1 extends RuntimeException {

    public UserExceptionV1() {
        super();
    }

    public UserExceptionV1(String message) {
        super(message);
    }

    public UserExceptionV1(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExceptionV1(Throwable cause) {
        super(cause);
    }

    protected UserExceptionV1(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
