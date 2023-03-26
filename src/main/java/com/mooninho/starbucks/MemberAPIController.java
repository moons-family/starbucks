package com.mooninho.starbucks;

import com.mooninho.starbucks.dto.MemberDTO;
import com.mooninho.starbucks.dto.MemberJoinDTO;
import com.mooninho.starbucks.dto.MemberLoginDTO;
import com.mooninho.starbucks.entity.DeleteMemberInfo;
import com.mooninho.starbucks.entity.Member;
import com.mooninho.starbucks.exception.UserException;
import com.mooninho.starbucks.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public Long findMember(@RequestBody @Valid MemberLoginDTO memberLoginDTO, HttpServletRequest request) {

        Member loginMember = memberService.findMember(memberLoginDTO);

        if (loginMember == null) {
            throw new UserException("존재하지 않는 회원입니다.");
        }

        if (loginMember.getLoginFailCount() >= 5) {
            throw new UserException("계정이 잠겼습니다. 고객센터에 문의해주세요.");
        }

        if (!loginMember.getPassword().equals(memberLoginDTO.getPassword())) {
            throw new UserException("비밀번호가 일치하지 않습니다. 현재 시도 횟수 : ( " + loginMember.getLoginFailCount() + " / 5 )");
        }

        MemberDTO memberInfo = new MemberDTO(loginMember);

        HttpSession session = request.getSession();
        session.setAttribute("memberInfo", memberInfo);

        return loginMember.getId();
    }

    @GetMapping
    public String loginCheck(@SessionAttribute(name = "memberInfo", required = false) MemberDTO memberInfo) {

        if (memberInfo == null) {
            return "게스트로 입장하셨습니다.";
        }

        return memberInfo.getName() + "님 환영합니다!";
    }

    @PostMapping("/delete")
    public String deleteMember(
            @SessionAttribute(name = "memberInfo", required = false) MemberDTO memberInfo,
            @RequestBody @Valid DeleteMemberInfo deleteMemberInfo,
            HttpSession session
    ) {
        if(memberInfo == null) {
            throw new UserException("회원정보가 존재하지 않습니다.");
        }

        DeleteMemberInfo deletedMember =
                memberService.deleteMember(memberInfo.getId(), deleteMemberInfo.getReason(), session);

        return "탈퇴 처리되었습니다. 회원 번호 : " + deletedMember.getMemberId()
                + " / 탈퇴 사유 : " + deletedMember.getReason();
    }
}
