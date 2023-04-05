package com.mooninho.starbucks.v2.dto;

import com.mooninho.starbucks.v2.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDto {

    private String email;
    private String password;
    private String name;
    private String phone;

    private MemberDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static MemberDto createMemberDto(String email, String password) {
        return new MemberDto(email, password);
    }

    public Member toMember() {
        return Member.createMember(email, password, name, phone);
    }

}
