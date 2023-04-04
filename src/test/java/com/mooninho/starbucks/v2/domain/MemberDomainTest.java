package com.mooninho.starbucks.v2.domain;

import com.mooninho.starbucks.v2.exception.UserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@DisplayName("[유닛 테스트] - 회원 도메인")
class MemberDomainTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("회원 정보 - 이메일 미입력시 예외 발생")
    void nullOrEmptyEmail_throwException(String email) {
        //given & when
        Throwable throwable = catchThrowable(() -> Email.of(email));
        
        //then
        assertThat(throwable).isInstanceOf(UserException.class);
        assertThat(throwable.getMessage()).isEqualTo("아이디를 입력하세요.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("회원 정보 - 비밀번호 미입력시 예외 발생")
    void nullOrEmptyPassword_throwException(String password) {
        //given & when
        Throwable throwable = catchThrowable(() -> Password.of(password));

        //then
        assertThat(throwable).isInstanceOf(UserException.class);
        assertThat(throwable.getMessage()).isEqualTo("비밀번호를 입력하세요.");
    }
}