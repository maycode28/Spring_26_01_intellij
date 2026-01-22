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
    public String doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email){
        Member member = memberService.getMemberByLoginId(loginId);
        if(member!=null){
            return "중복된 아이디입니다.";
        }
        memberService.doJoin(loginId, loginPw,name,nickname,cellphoneNum,email);
        return nickname+"님 가입 완료!";
    }
}
