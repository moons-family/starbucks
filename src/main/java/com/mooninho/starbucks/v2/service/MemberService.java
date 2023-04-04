package com.mooninho.starbucks.v2.service;

import com.mooninho.starbucks.v2.dto.MemberDto;
import com.mooninho.starbucks.v2.entity.Member;
import com.mooninho.starbucks.v2.exception.UserException;
import com.mooninho.starbucks.v2.repository.MemberQueryRepository;
import com.mooninho.starbucks.v2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

    @Transactional
    public Long signUp(MemberDto memberDto) {

        boolean emailExist = memberQueryRepository.isEmailExist(memberDto.getEmail());
        if (emailExist) {
            throw new UserException("이미 사용중인 이메일 입니다.");
        }

        Member member = memberRepository.save(memberDto.toMember());

        return member.getMemberId();
    }
}
