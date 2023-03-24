package com.mooninho.starbucks.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@NoArgsConstructor
@SequenceGenerator(name = "deleteMemberInfo_seq_gen", sequenceName = "deleteMemberInfo_seq")
public class DeleteMemberInfo {

    @Id
    @GeneratedValue(generator = "deleteMemberInfo_seq_gen")
    @Column(name = "delete_id")
    private Long id;
    @Column(name = "member_id")
    private Long userId;

    @NotBlank(message = "탈퇴사유를 입력해주세요.")
    private String reason;

    @Builder
    public DeleteMemberInfo(Long userId, String reason) {
        this.userId = userId;
        this.reason = reason;
    }
}
