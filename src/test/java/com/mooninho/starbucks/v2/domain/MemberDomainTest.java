package com.mooninho.starbucks.v2.domain;

import com.mooninho.starbucks.v2.dto.MemberDto;
import com.mooninho.starbucks.v2.dto.MemberJoinDto;
import com.mooninho.starbucks.v2.exception.UserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@DisplayName("[유닛 테스트] - 회원 도메인")
class MemberDomainTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("회원 정보 - 이메일 미입력시 예외 발생")
    void nullOrEmptyEmail_throwException(String email) throws Exception {
        //given & when
        Throwable throwable = catchThrowable(() -> Email.of(email));
        
        //then
        assertThat(throwable).isInstanceOf(UserException.class);
        assertThat(throwable.getMessage()).isEqualTo("아이디를 입력하세요.");
    }

    @Test
    @DisplayName("회원 정보 - 잘못된 유형의 이메일 입력시 예외 발생")
    void invalidEmail_setInfo_throwException() throws Exception {
        //given & when
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail("testEmail");

        //when
        Throwable throwable = catchThrowable(() -> MemberJoinDto.setMemberInfo(memberDto));

        //then
        assertThat(throwable).isInstanceOf(UserException.class);
        assertThat(throwable.getMessage()).isEqualTo("이메일 형식이 올바르지 않습니다.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("회원 정보 - 비밀번호 미입력시 예외 발생")
    void nullOrEmptyPassword_throwException(String password) throws Exception {
        //given & when
        Throwable throwable = catchThrowable(() -> Password.of(password));

        //then
        assertThat(throwable).isInstanceOf(UserException.class);
        assertThat(throwable.getMessage()).isEqualTo("비밀번호를 입력하세요.");
    }

    @Test
    @DisplayName("회원 정보 - 잘못된 유형의 비밀번호 입력시 예외 발생")
    void invalidPassword_setInfo_throwException() throws Exception {
        //given
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail("Test@naver.com");
        memberDto.setPassword("testPassword");

        //when
        Throwable throwable = catchThrowable(() -> MemberJoinDto.setMemberInfo(memberDto));

        //then
        assertThat(throwable).isInstanceOf(UserException.class);
        assertThat(throwable.getMessage()).isEqualTo("8자 이상, 대/소문자, 숫자, 특수문자가 포함되어야 합니다.");
    }

    @Test
    @DisplayName("올바른 유형의 이메일, 비밀번호 입력시 회원정보 생성")
    void properEmailAndPassword_setInfo_createMemberInfo () throws Exception {
        //given
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail("Test@naver.com");
        memberDto.setPassword("Test123!");

        //when
        MemberJoinDto memberJoinDto = MemberJoinDto.setMemberInfo(memberDto);

        //then
        assertThat(memberJoinDto).isNotNull();
    }
}