package com.mooninho.starbucks.entity;

import com.mooninho.starbucks.status.MemberStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;

    private String name;
    private String phone;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    public Member(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    public Member(String email, String password, String name, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }
}
