package com.mooninho.starbucks.service;

import com.mooninho.starbucks.dto.MemberLoginDTO;
import com.mooninho.starbucks.entity.Member;
import com.mooninho.starbucks.repository.MemberRepository;
import com.mooninho.starbucks.status.MemberStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Transactional
    public Member login(MemberLoginDTO memberLoginDTO) {

        Member member = memberRepository.findByEmailAndPassword(
                memberLoginDTO.getEmail(),
                memberLoginDTO.getPassword()
        );

        return member;
    }
}
