package com.mooninho.starbucks.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;

    private String name;
    private String phone;

    public Member() {
    }

    public Member(String loginId, String password, String name, String phone) {
        this.email = loginId;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }
}
