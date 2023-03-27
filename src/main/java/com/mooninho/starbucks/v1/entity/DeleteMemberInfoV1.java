package com.mooninho.starbucks.v1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@NoArgsConstructor
public class DeleteMemberInfoV1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;

    @NotBlank(message = "탈퇴사유를 입력해주세요.")
    private String reason;

    private DeleteMemberInfoV1(Long memberId, String reason) {
        this.memberId = memberId;
        this.reason = reason;
    }

    public static DeleteMemberInfoV1 createDeletedMemberInfo(Long memberId, String reason) {

        return new DeleteMemberInfoV1(memberId, reason);
    }
}
