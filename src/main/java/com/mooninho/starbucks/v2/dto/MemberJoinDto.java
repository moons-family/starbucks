package com.mooninho.starbucks.v2.dto;

import com.mooninho.starbucks.v2.entity.Member;
import com.mooninho.starbucks.v2.exception.UserException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberJoinDto {

    private String email;
    private String password;
    private String name;
    private String phone;

    private MemberJoinDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static MemberJoinDto setMemberInfo(MemberDto memberDto) {

        if (!memberDto.getEmail().matches("^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$")) {
            throw new UserException("이메일 형식이 올바르지 않습니다.");
        }

        if (!memberDto.getPassword().matches("(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}")) {
            throw new UserException("8자 이상, 대/소문자, 숫자, 특수문자가 포함되어야 합니다.");
        }
        return new MemberJoinDto(memberDto.getEmail(), memberDto.getPassword());
    }

    public Member toMember() {
        return Member.createMember(email, password, name, phone);
    }
}