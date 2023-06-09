package com.mooninho.starbucks.v1.dto;

import com.mooninho.starbucks.v1.entity.MemberV1;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class MemberJoinDTOV1 {

    @NotBlank(message = "아이디를 입력하세요.")
    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$",
            message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
            message = "8자 이상, 대/소문자, 숫자, 특수문자가 포함되어야 합니다.")
    private String password;

    private String name;

    private String phone;

    public static MemberV1 createMember(MemberJoinDTOV1 memberJoinDTO) {
        MemberV1 member = MemberV1.builder()
                .email(memberJoinDTO.getEmail())
                .password(memberJoinDTO.getPassword())
                .name(memberJoinDTO.getName())
                .phone(memberJoinDTO.getPhone())
                .build();

        return member;
    }
}