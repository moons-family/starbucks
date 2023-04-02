package com.mooninho.starbucks.v2.controller;

import com.mooninho.starbucks.v2.dto.MemberDto;
import com.mooninho.starbucks.v2.dto.MemberJoinDto;
import com.mooninho.starbucks.v2.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberAPIController {

    private final MemberService memberService;

    @PostMapping("/join")
    public void joinMember(@RequestBody MemberDto memberDto) {

        MemberJoinDto memberJoinDto = MemberJoinDto.setMemberInfo(memberDto);

        memberService.join(memberJoinDto);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Long> signUp(@RequestBody MemberDto memberDto) {
        Long memberId = memberService.signUp(memberDto);
        return ResponseEntity.ok(memberId);
    }
}
