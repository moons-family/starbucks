package com.mooninho.starbucks.dto;

import com.mooninho.starbucks.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String phone;

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.name = member.getName();
        this.phone = member.getPhone();
    }
}
