package com.mooninho.starbucks;

import com.mooninho.starbucks.dto.MemberDTO;
import com.mooninho.starbucks.entity.Member;
import com.mooninho.starbucks.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberAPIController {

    private final MemberService memberService;

    @PostMapping("join")
    public void joinMember(@RequestBody @Valid MemberDTO memberDto) {

        Member member = createMember(memberDto);

        memberService.join(member);
    }

    private static Member createMember(MemberDTO memberDto) {
        Member member = new Member(
                memberDto.getId(),
                memberDto.getPassword(),
                memberDto.getUsername(),
                memberDto.getPhone()
        );
        return member;
    }
}
