package com.mooninho.starbucks.v2.entity;

import com.mooninho.starbucks.v2.dto.MemberJoinDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;

    private String name;
    private String phone;

    private Member(String email, String password, String name, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public static Member createMember(MemberJoinDto memberJoinDto) {

        return new Member(
                memberJoinDto.getEmail()
                , memberJoinDto.getPassword()
                , memberJoinDto.getName()
                , memberJoinDto.getPhone()
        );
    }
}
