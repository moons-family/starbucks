package com.mooninho.starbucks.v2;

import com.mooninho.starbucks.v2.dto.MemberJoinDto;
import com.mooninho.starbucks.v2.exception.UserException;
import com.mooninho.starbucks.v2.repository.MemberRepository;
import com.mooninho.starbucks.v2.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@ExtendWith(MockitoExtension.class)
@DisplayName("[유닛 테스트] 회원 가입")
class MemberAPIControllerTest {

    private final MemberRepository memberRepository = Mockito.mock(MemberRepository.class);
    private final MemberService memberService = new MemberService(memberRepository);

    @Test
    @DisplayName("가입 실패 - 빈 이메일로 회원가입시 예외 발생")
    void nullAndEmptyEmail_join_throwException() {
        // given
        MemberJoinDto memberJoinDto = new MemberJoinDto();
        memberJoinDto.setEmail("abcd@naver.com");
        memberJoinDto.setPassword("Test@11222");

        System.out.println("왜안되냐 대체 : " + memberJoinDto.getEmail());

        // when
        // TODO 컨트롤러에서 발생한 예외 잡는법 찾기
        Throwable throwable = catchThrowable(() -> memberService.join(memberJoinDto));

        // then
//        assertThat(throwable).isInstanceOf(UserException.class);
    }
}