package com.mooninho.starbucks.entity;

import com.mooninho.starbucks.dto.MemberJoinDTO;
import lombok.Builder;
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

    private int loginFailCount = 0;

    private Member(MemberJoinDTO memberJoinDTO) {
        this.email = memberJoinDTO.getEmail();
        this.password = memberJoinDTO.getPassword();
        this.name = memberJoinDTO.getName();
        this.phone = memberJoinDTO.getPhone();
    }

    public static Member createMember(MemberJoinDTO memberJoinDTO) {
        return new Member(memberJoinDTO);
    }

    public void loginFail() {
        this.loginFailCount += 1;
    }

    public void resetLoginFailCount() {
        this.loginFailCount = 0;
    }
}
