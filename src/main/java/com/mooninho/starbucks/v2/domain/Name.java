package com.mooninho.starbucks.v2.domain;

import lombok.Getter;

@Getter
public class Name {

    private final String name;

    private Name() {
        this("");
    }

    private Name(String name) {
        this.name = name;
    }

    public static Name of(String name) {
        return new Name(name);
    }
}
