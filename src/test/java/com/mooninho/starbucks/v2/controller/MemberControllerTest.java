package com.mooninho.starbucks.v2.controller;

import com.mooninho.starbucks.v2.dto.MemberDto;
import com.mooninho.starbucks.v2.exception.UserException;
import com.mooninho.starbucks.v2.repository.MemberQueryRepository;
import com.mooninho.starbucks.v2.repository.MemberRepository;
import com.mooninho.starbucks.v2.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.mock;

@DisplayName("[유닛 테스트] - 회원 컨트롤러")
public class MemberControllerTest {

    private final MemberRepository memberRepository = mock(MemberRepository.class);
    private final MemberQueryRepository memberQueryRepository = mock(MemberQueryRepository.class);
    private final MemberService memberService = new MemberService(memberRepository, memberQueryRepository);
    private final MemberAPIController memberAPIController = new MemberAPIController(memberService);

    @Test
    @DisplayName("회원 가입 - 올바르지 않은 형식의 이메일 입력시 예외 발생")
    void invalidEmail_join_throwException() {
        //given
        MemberDto memberDto = MemberDto.createMemberDto("Test", "Test123!");

        //when
        Throwable throwable = catchThrowable(() -> memberAPIController.signUp(memberDto));

        //then
        assertThat(throwable).isInstanceOf(UserException.class);
        assertThat(throwable).hasMessage("이메일 형식이 올바르지 않습니다.");
    }

    @Test
    @DisplayName("회원 가입 - 올바르지 않은 형식의 비밀번호 입력시 예외 발생")
    void invalidPassword_join_throwException() {
        //given
        MemberDto memberDto = MemberDto.createMemberDto("Test@naver.com", "TestPassword");

        //when
        Throwable throwable = catchThrowable(() -> memberAPIController.signUp(memberDto));

        //then
        assertThat(throwable).isInstanceOf(UserException.class);
        assertThat(throwable).hasMessage("8자 이상, 대/소문자, 숫자, 특수문자가 포함되어야 합니다.");
    }

    //TODO MockMvc 학습 후 controller 테스트코드 작성
}
