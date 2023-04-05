package com.mooninho.starbucks.v1.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class MemberV1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;

    private String name;
    private String phone;

    private int loginFailCount = 0;

    @Builder
    private MemberV1(String email, String password, String name, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public void loginFail() {
        this.loginFailCount += 1;
    }

    public void resetLoginFailCount() {
        this.loginFailCount = 0;
    }
}
