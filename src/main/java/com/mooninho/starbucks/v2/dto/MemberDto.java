package com.mooninho.starbucks.v2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    private String email;
    private String password;
    private String name;
    private String phone;
}
