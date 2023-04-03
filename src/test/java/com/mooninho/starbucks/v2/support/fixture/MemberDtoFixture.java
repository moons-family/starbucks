package com.mooninho.starbucks.v2.support.fixture;

import com.mooninho.starbucks.v2.dto.MemberDto;
import com.mooninho.starbucks.v2.dto.MemberJoinDto;

public class MemberDtoFixture {

    public static MemberJoinDto getMemberJoinDto() {
        MemberDto memberDto = MemberDtoFixture.createMemberDto();
        MemberJoinDto memberJoinDto = MemberJoinDto.setMemberInfo(memberDto);
        return memberJoinDto;
    }

    public static MemberDto createMemberDto(String email, String password) {
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail(email);
        memberDto.setPassword(password);
        return memberDto;
    }

    public static MemberDto createMemberDto() {
        return createMemberDto("test@naver.com", "Test123!");
    }
}
