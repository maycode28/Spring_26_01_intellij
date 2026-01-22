package com.example.demo.controller;

import com.example.demo.service.MemberService;
import com.example.demo.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrMemberController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("/usr/member/doJoin")
    @ResponseBody
    public String doJoin(String loginId, String loginPw, String loginPwChk, String name, String nickname, String cellphoneNum, String email){

        if(loginId.isBlank()){
            return "아이디를 입력하세요";
        } else if (loginPw.isBlank()) {
            return "비밀번호를 입력하세요";
        }else if (name.isBlank()) {
            return "이름을 입력하세요";
        }else if (nickname.isBlank()) {
            return "별명을 입력하세요";
        }else if (cellphoneNum.isBlank()) {
            return "전화번호를 입력하세요";
        }else if (email.isBlank()) {
            return "이메일을 입력하세요";
        }

        if (!loginPw.equals(loginPwChk)){
            return "비밀번호와 비밀번호 확인이 일치하지 않습니다.";
        }
        Member member = memberService.getMemberByLoginId(loginId);
        if(member!=null){
            return "중복된 아이디입니다.";
        }
        member = memberService.getMemberByEmail(email);
        if(member!=null){
            return "중복된 이메일입니다.";
        }

        memberService.doJoin(loginId, loginPw,name,nickname,cellphoneNum,email);
        return nickname+"님 가입 완료!";
    }
}
