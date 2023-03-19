package com.mooninho.starbucks.service;

import com.mooninho.starbucks.dto.MemberJoinDTO;
import com.mooninho.starbucks.dto.MemberLoginDTO;
import com.mooninho.starbucks.entity.Member;
import com.mooninho.starbucks.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void join(MemberJoinDTO memberJoinDTO) {

        Member member = createMember(memberJoinDTO);

        memberRepository.save(member);
    }

    @Transactional
    public Member login(MemberLoginDTO memberLoginDTO) {

        Member member = memberRepository.findByEmail(memberLoginDTO.getEmail());

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

    public Member createMember(MemberJoinDTO memberJoinDto) {
        return new Member(
                memberJoinDto.getEmail(),
                memberJoinDto.getPassword(),
                memberJoinDto.getName(),
                memberJoinDto.getPhone()
        );
    }
}