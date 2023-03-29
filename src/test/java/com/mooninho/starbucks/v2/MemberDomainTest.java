package com.mooninho.starbucks.v2;

import com.mooninho.starbucks.v2.dto.MemberJoinDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DisplayName("[유닛 테스트] 회원 가입")
class MemberDomainTest {

    private static Validator validator;
    private static ValidatorFactory factory;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("이메일 미입력시 경고 메세지 출력")
    void nullAndEmptyEmail_returnWarnMessage(String email) {
        //given
        MemberJoinDto memberJoinDto = new MemberJoinDto();
        memberJoinDto.setEmail(email);

        //when
        Set<ConstraintViolation<MemberJoinDto>> violations = validator.validate(memberJoinDto);
        ConstraintViolation<MemberJoinDto> violation = violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("email"))
                .findFirst()
                .get();

        //then
        assertThat(violation.getMessage()).isEqualTo("아이디를 입력하세요.");
    }

    @Test
    @DisplayName("올바르지 않은 이메일 입력시 경고 메세지 출력")
    void invalidEmail_returnWarnMessage() {
        //given & when
        MemberJoinDto memberJoinDto = new MemberJoinDto();
        memberJoinDto.setEmail("emailtest");

        Set<ConstraintViolation<MemberJoinDto>> violations = validator.validate(memberJoinDto);
        ConstraintViolation<MemberJoinDto> violation = violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("email"))
                .findFirst()
                .get();

        //then
        assertThat(violation.getMessage()).isEqualTo("이메일 형식이 올바르지 않습니다.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("비밀번호 미입력시 경고 메세지 출력")
    void nullAndEmptyPassword_returnWarnMessage(String password) {
        //given
        MemberJoinDto memberJoinDto = new MemberJoinDto();
        memberJoinDto.setPassword(password);

        //when
        Set<ConstraintViolation<MemberJoinDto>> violations = validator.validate(memberJoinDto);
        ConstraintViolation<MemberJoinDto> violation = violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("password"))
                .findFirst()
                .get();

        //then
        assertThat(violation.getMessage()).isEqualTo("비밀번호를 입력하세요.");
    }

    @Test
    @DisplayName("올바르지 않은 비밀번호 입력시 경고 메세지 출력")
    void invalidPassword_returnWarnMessage() {
        //given & when
        MemberJoinDto memberJoinDto = new MemberJoinDto();
        memberJoinDto.setPassword("passwordtest");

        Set<ConstraintViolation<MemberJoinDto>> violations = validator.validate(memberJoinDto);
        ConstraintViolation<MemberJoinDto> violation = violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("password"))
                .findFirst()
                .get();

        //then
        assertThat(violation.getMessage()).isEqualTo("8자 이상, 대/소문자, 숫자, 특수문자가 포함되어야 합니다.");
    }


    //TODO 검증 성공한값이 필드에 주입되었을 경우에만 객체생성하는 구조로 변경
    @Test
    @DisplayName("올바른 가입 형식 입력시에만 회원정보 생성")
    void properEmailAndPassword_returnMemberInfo() {
        //given & when
        MemberJoinDto memberJoinDto = new MemberJoinDto();
        memberJoinDto.setEmail("abcd@naver.com");
        memberJoinDto.setPassword("Test@11222");

        Set<ConstraintViolation<MemberJoinDto>> violations = validator.validate(memberJoinDto);
//        ConstraintViolation<MemberJoinDto> violation = violations.stream()


        //then
        assertThat(memberJoinDto).isNotNull();
    }
}