package com.mooninho.starbucks.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
public class MemberLoginDTO {

    @NotBlank(message = "아이디를 입력하세요")
    private String email;
    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;

}
