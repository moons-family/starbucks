package com.mooninho.starbucks.v2.controller;

import com.mooninho.starbucks.v2.dto.MemberDto;
import com.mooninho.starbucks.v2.exception.UserException;
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
    public ResponseEntity<Long> signUp(@RequestBody MemberDto memberDto) {

        if (!memberDto.getEmail().matches("^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$")) {
            throw new UserException("이메일 형식이 올바르지 않습니다.");
        }

        if (!memberDto.getPassword().matches("(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}")) {
            throw new UserException("8자 이상, 대/소문자, 숫자, 특수문자가 포함되어야 합니다.");
        }

        Long memberId = memberService.signUp(memberDto);

        return ResponseEntity.ok(memberId);
    }
}
