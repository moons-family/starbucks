package com.mooninho.starbucks;

import com.mooninho.starbucks.dto.MemberJoinDTO;
import com.mooninho.starbucks.dto.MemberLoginDTO;
import com.mooninho.starbucks.entity.Member;
import com.mooninho.starbucks.exception.UserException;
import com.mooninho.starbucks.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberAPIController {

    private final MemberService memberService;

    @PostMapping("/join")
    public void joinMember(@RequestBody @Valid MemberJoinDTO memberJoinDto) {

        memberService.join(memberJoinDto);
    }

    @PostMapping("/login")
    public Long findMember(@RequestBody @Valid MemberLoginDTO memberLoginDTO) {

        Member member = memberService.login(memberLoginDTO);

        if (member == null) {
            throw new UserException("존재하지 않는 회원입니다.");
        }

        if (member.getLoginFailCount() >= 5) {
            throw new UserException("계정이 잠겼습니다. 고객센터에 문의해주세요.");
        }

        if (!member.getPassword().equals(memberLoginDTO.getPassword())) {
            throw new UserException("비밀번호가 일치하지 않습니다. 현재 시도 횟수 : ( " + member.getLoginFailCount() + " / 5 )");
        }

        return member.getId();
    }
}
