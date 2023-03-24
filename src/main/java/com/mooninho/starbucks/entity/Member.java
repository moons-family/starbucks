package com.mooninho.starbucks.entity;

import com.mooninho.starbucks.dto.MemberJoinDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@SequenceGenerator(name = "member_seq_gen", sequenceName = "member_seq")
public class Member {

    @Id
    @GeneratedValue(generator = "member_seq_gen")
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;

    private String name;
    private String phone;

    private int loginFailCount = 0;

    @Builder
    private Member(String email, String password, String name, String phone) {
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
