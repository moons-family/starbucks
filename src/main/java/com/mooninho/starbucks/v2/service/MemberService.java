package com.mooninho.starbucks.v2.service;

import com.mooninho.starbucks.v2.dto.MemberJoinDto;
import com.mooninho.starbucks.v2.entity.Member;
import com.mooninho.starbucks.v2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void join(MemberJoinDto memberJoinDto) {

        Member member = Member.createMember(memberJoinDto);

        memberRepository.save(member);
    }
}
