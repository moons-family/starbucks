package com.mooninho.starbucks.service;

import com.mooninho.starbucks.dto.MemberJoinDTO;
import com.mooninho.starbucks.dto.MemberLoginDTO;
import com.mooninho.starbucks.entity.Member;
import com.mooninho.starbucks.entity.DeleteMemberInfo;
import com.mooninho.starbucks.repository.MemberDeleteInfoRepository;
import com.mooninho.starbucks.repository.MemberRepository;
import com.mooninho.starbucks.repository.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;
    private final MemberDeleteInfoRepository memberDeleteInfoRepository;

    @Transactional
    public void join(MemberJoinDTO memberJoinDTO) {

        Member member = Member.createMember(memberJoinDTO);

        memberRepository.save(member);
    }

    @Transactional
    public Member findMember(MemberLoginDTO memberLoginDTO) {

        Member member = memberQueryRepository.findMemberByEmail(memberLoginDTO.getEmail());

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
    public DeleteMemberInfo deleteMember(Long id, String reason, HttpSession session) {

        DeleteMemberInfo deleteMemberInfo = DeleteMemberInfo.createDeletedMemberInfo(id, reason);
        memberDeleteInfoRepository.save(deleteMemberInfo);

        memberQueryRepository.deleteMember(id);
        session.invalidate();

        return deleteMemberInfo;
    }
}