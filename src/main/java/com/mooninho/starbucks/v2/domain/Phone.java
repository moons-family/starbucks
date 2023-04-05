package com.mooninho.starbucks.v2.domain;

import lombok.Getter;

@Getter
public class Phone {

    private final String phone;

    private Phone() {
        this("");
    }

    private Phone(String phone) {
        this.phone = phone;
    }

    public static Phone of(String phone) {
        return new Phone(phone);
    }
}
