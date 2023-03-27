package com.mooninho.starbucks.v1.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class MemberLoginDTOV1 {

    @NotBlank(message = "아이디를 입력하세요")
    private String email;
    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;

}
