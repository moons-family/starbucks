package com.mooninho.starbucks.v1;

import com.mooninho.starbucks.v1.dto.MemberDTOV1;
import com.mooninho.starbucks.v1.dto.MemberJoinDTOV1;
import com.mooninho.starbucks.v1.dto.MemberLoginDTOV1;
import com.mooninho.starbucks.v1.entity.DeleteMemberInfoV1;
import com.mooninho.starbucks.v1.entity.MemberV1;
import com.mooninho.starbucks.v1.exception.UserExceptionV1;
import com.mooninho.starbucks.v1.service.MemberServiceV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/member/v1")
@RequiredArgsConstructor
public class MemberAPIControllerV1 {

    private final MemberServiceV1 memberService;

    @PostMapping("/join")
    public void joinMember(@RequestBody @Valid MemberJoinDTOV1 memberJoinDto) {

        memberService.join(memberJoinDto);
    }

    @PostMapping("/login")
    public Long findMember(@RequestBody @Valid MemberLoginDTOV1 memberLoginDTO, HttpServletRequest request) {

        MemberV1 loginMember = memberService.findMember(memberLoginDTO);

        if (loginMember == null) {
            throw new UserExceptionV1("존재하지 않는 회원입니다.");
        }

        if (loginMember.getLoginFailCount() >= 5) {
            throw new UserExceptionV1("계정이 잠겼습니다. 고객센터에 문의해주세요.");
        }

        if (!loginMember.getPassword().equals(memberLoginDTO.getPassword())) {
            throw new UserExceptionV1("비밀번호가 일치하지 않습니다. 현재 시도 횟수 : ( " + loginMember.getLoginFailCount() + " / 5 )");
        }

        MemberDTOV1 memberInfo = new MemberDTOV1(loginMember);

        HttpSession session = request.getSession();
        session.setAttribute("memberInfo", memberInfo);

        return loginMember.getId();
    }

    @GetMapping
    public String loginCheck(@SessionAttribute(name = "memberInfo", required = false) MemberDTOV1 memberInfo) {

        if (memberInfo == null) {
            return "게스트로 입장하셨습니다.";
        }

        return memberInfo.getName() + "님 환영합니다!";
    }

    @PostMapping("/delete")
    public String deleteMember(
            @SessionAttribute(name = "memberInfo", required = false) MemberDTOV1 memberInfo,
            @RequestBody @Valid DeleteMemberInfoV1 deleteMemberInfo,
            HttpSession session
    ) {
        if(memberInfo == null) {
            throw new UserExceptionV1("회원정보가 존재하지 않습니다.");
        }

        DeleteMemberInfoV1 deletedMember =
                memberService.deleteMember(memberInfo.getId(), deleteMemberInfo.getReason(), session);

        return "탈퇴 처리되었습니다. 회원 번호 : " + deletedMember.getMemberId()
                + " / 탈퇴 사유 : " + deletedMember.getReason();
    }
}
