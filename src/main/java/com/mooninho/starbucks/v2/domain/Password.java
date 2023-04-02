package com.mooninho.starbucks.v2.domain;

import com.mooninho.starbucks.v2.exception.UserException;
import lombok.Getter;

@Getter
public class Password {

    private final String password;

    private Password() {
        this("");
    }

    private Password(String password) {
        this.password = password;
    }

    public static Password of(String password) {

        if (password == null || password.isBlank()) {
            throw new UserException("비밀번호를 입력하세요.");
        }

        return new Password(password);
    }
}
