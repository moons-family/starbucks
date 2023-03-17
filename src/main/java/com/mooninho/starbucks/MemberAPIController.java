package com.mooninho.starbucks;

import com.mooninho.starbucks.dto.MemberJoinDTO;
import com.mooninho.starbucks.dto.MemberLoginDTO;
import com.mooninho.starbucks.entity.Member;
import com.mooninho.starbucks.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberAPIController {

    private final MemberService memberService;

    @PostMapping("/join")
    public void joinMember(@RequestBody @Valid MemberJoinDTO memberJoinDto) {

        Member member = createMember(memberJoinDto);

        memberService.join(member);
    }

    @PostMapping("/login")
    public String loginMember(@RequestBody @Valid MemberLoginDTO memberLoginDTO) {

        Member login = memberService.login(memberLoginDTO);
        if (login == null) {
            log.info("로그인 실패");
        }
        return login.getEmail();
    }

    private static Member createMember(MemberJoinDTO memberJoinDto) {
        Member member = new Member(
                memberJoinDto.getEmail(),
                memberJoinDto.getPassword(),
                memberJoinDto.getName(),
                memberJoinDto.getPhone()
        );
        return member;
    }
}
