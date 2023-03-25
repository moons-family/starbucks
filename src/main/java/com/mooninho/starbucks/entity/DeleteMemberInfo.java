package com.mooninho.starbucks.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@NoArgsConstructor
public class DeleteMemberInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;

    @NotBlank(message = "탈퇴사유를 입력해주세요.")
    private String reason;

    public DeleteMemberInfo(Long memberId, String reason) {
        this.memberId = memberId;
        this.reason = reason;
    }
}
