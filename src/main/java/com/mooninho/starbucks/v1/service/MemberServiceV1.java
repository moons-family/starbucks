package com.mooninho.starbucks.v1.service;

import com.mooninho.starbucks.v1.dto.MemberJoinDTOV1;
import com.mooninho.starbucks.v1.dto.MemberLoginDTOV1;
import com.mooninho.starbucks.v1.entity.DeleteMemberInfoV1;
import com.mooninho.starbucks.v1.entity.MemberV1;
import com.mooninho.starbucks.v1.repository.MemberDeleteInfoRepositoryV1;
import com.mooninho.starbucks.v1.repository.MemberRepositoryV1;
import com.mooninho.starbucks.v1.repository.MemberQueryRepositoryV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceV1 {

    private final MemberRepositoryV1 memberRepository;
    private final MemberQueryRepositoryV1 memberQueryRepository;
    private final MemberDeleteInfoRepositoryV1 memberDeleteInfoRepository;

    @Transactional
    public void join(MemberJoinDTOV1 memberJoinDTO) {

        MemberV1 member = MemberJoinDTOV1.createMember(memberJoinDTO);

        memberRepository.save(member);
    }

    @Transactional
    public MemberV1 findMember(MemberLoginDTOV1 memberLoginDTO) {

        MemberV1 member = memberQueryRepository.findMemberByEmail(memberLoginDTO.getEmail());

        if (member == null) {
            return null;
        }

        if (member.getLoginFailCount() < 5 && member.getPassword().equals(memberLoginDTO.getPassword())) {
            member.resetLoginFailCount();
        }

        if (!member.getPassword().equals(memberLoginDTO.getPassword())) {
            member.loginFail();
        }

        return member;
    }

    @Transactional
    public DeleteMemberInfoV1 deleteMember(Long id, String reason, HttpSession session) {

        DeleteMemberInfoV1 deleteMemberInfo = DeleteMemberInfoV1.createDeletedMemberInfo(id, reason);
        memberDeleteInfoRepository.save(deleteMemberInfo);

        memberQueryRepository.deleteMember(id);
        session.invalidate();

        return deleteMemberInfo;
    }
}