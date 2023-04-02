package com.mooninho.starbucks.v2.dto;

import com.mooninho.starbucks.v2.entity.Member;
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

    public Member toMember() {
        return Member.createMember(
                email,
                password,
                name,
                phone
        );
    }
}
