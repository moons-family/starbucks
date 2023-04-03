package com.mooninho.starbucks.v2.service;

import com.mooninho.starbucks.v2.dto.MemberJoinDto;
import com.mooninho.starbucks.v2.entity.Member;
import com.mooninho.starbucks.v2.exception.UserException;
import com.mooninho.starbucks.v2.repository.MemberQueryRepository;
import com.mooninho.starbucks.v2.repository.MemberRepository;
import com.mooninho.starbucks.v2.support.fixture.MemberDtoFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("[유닛 테스트] - 회원 서비스")
public class MemberServiceTest {

    private final MemberRepository memberRepository = mock(MemberRepository.class);
    private final MemberQueryRepository memberQueryRepository = mock(MemberQueryRepository.class);
    private final MemberService memberService = new MemberService(memberRepository, memberQueryRepository);

    @Test
    @DisplayName("회원가입 - 올바른 회원정보로 가입시 회원생성")
    void memberInfo_join_createMember() {
        //given
        MemberJoinDto memberJoinDto = MemberDtoFixture.getMemberJoinDto();
        Member member = memberJoinDto.toMember();
        given(memberRepository.save(any()))
                .willReturn(member);

        // when
        Member savedMember = memberRepository.save(member);

        //then
        assertThat(savedMember).isInstanceOf(Member.class);
    }

    @Test
    @DisplayName("회원 가입 - 이메일 중복시 예외 발생")
    void existsEmail_join_throwException() {
        //given
        MemberJoinDto memberJoinDto = MemberDtoFixture.getMemberJoinDto();
        given(memberQueryRepository.isEmailExist(any()))
                .willReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> memberService.signUp(memberJoinDto));

        //then
        assertThat(throwable).isInstanceOf(UserException.class);
        assertThat(throwable).hasMessage("이미 사용중인 이메일 입니다.");
    }
}
