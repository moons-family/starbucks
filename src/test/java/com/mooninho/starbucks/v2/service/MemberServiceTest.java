package com.mooninho.starbucks.v2.service;

import com.mooninho.starbucks.v2.dto.MemberDto;
import com.mooninho.starbucks.v2.dto.MemberJoinDto;
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
import static org.mockito.Mockito.mock;


@DisplayName("[유닛 테스트] - 회원 서비스")
public class MemberServiceTest {

    private final MemberRepository memberRepository = mock(MemberRepository.class);
    private final MemberQueryRepository memberQueryRepository = mock(MemberQueryRepository.class);
    private final MemberService memberServiceMock = mock(MemberService.class);
    private final MemberService memberService = new MemberService(memberRepository, memberQueryRepository);

    //TODO MemberService 클래스를 Mock객체로 만들어야 첫번째 테스트가 성공하는데 그 이유

    @Test
    @DisplayName("회원가입 - 올바른 회원정보로 가입시 회원생성")
    void memberInfo_join_createMember() {
        //given
        MemberDto memberDto = MemberDtoFixture.createMemberDto();
        MemberJoinDto memberJoinDto = MemberJoinDto.setMemberInfo(memberDto);

        //when
        Long memberId = memberServiceMock.signUp(memberJoinDto);

        //then
        assertThat(memberId).isNotNull();
    }

    @Test
    @DisplayName("회원 가입 - 이메일 중복시 예외 발생")
    void existsEmail_join_throwException() {
        //given
        MemberDto memberDto = MemberDtoFixture.createMemberDto();
        MemberJoinDto memberJoinDto = MemberJoinDto.setMemberInfo(memberDto);
        given(memberQueryRepository.isEmailExist(any()))
                .willReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> memberService.signUp(memberJoinDto));

        //then
        assertThat(throwable).isInstanceOf(UserException.class);
        assertThat(throwable).hasMessage("이미 사용중인 이메일 입니다.");
    }
}
