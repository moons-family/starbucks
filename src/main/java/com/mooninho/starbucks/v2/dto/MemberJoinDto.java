package com.mooninho.starbucks.v2.dto;

import com.mooninho.starbucks.v2.domain.Email;
import com.mooninho.starbucks.v2.domain.Name;
import com.mooninho.starbucks.v2.domain.Password;
import com.mooninho.starbucks.v2.domain.Phone;
import com.mooninho.starbucks.v2.exception.UserException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberJoinDto {

    private Email email;
    private Password password;
    private Name name;
    private Phone phone;

    private MemberJoinDto(Email email, Password password) {
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
        return new MemberJoinDto(Email.of(memberDto.getEmail()), Password.of(memberDto.getPassword()));
    }
}