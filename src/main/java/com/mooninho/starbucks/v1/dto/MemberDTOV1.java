package com.mooninho.starbucks.v1.dto;

import com.mooninho.starbucks.v1.entity.MemberV1;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTOV1 {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String phone;

    public MemberDTOV1(MemberV1 member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.name = member.getName();
        this.phone = member.getPhone();
    }
}
