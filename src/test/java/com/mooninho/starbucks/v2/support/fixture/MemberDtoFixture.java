package com.mooninho.starbucks.v2.support.fixture;

import com.mooninho.starbucks.v2.dto.MemberDto;

public class MemberDtoFixture {

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
