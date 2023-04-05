package com.mooninho.starbucks.v2.domain;

import com.mooninho.starbucks.v2.exception.UserException;
import lombok.Getter;

import javax.persistence.Column;

@Getter
public class Email {

    @Column(unique = true)
    private final String email;

    private Email() {
        this("");
    }

    private Email(String email) {
        this.email = email;
    }


    public static Email of(String email) {

        if (email == null || email.isBlank()) {
            throw new UserException("아이디를 입력하세요.");
        }

        return new Email(email);
    }
}
