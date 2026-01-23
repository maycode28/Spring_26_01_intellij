package com.example.demo.controller;

import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
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

        if(Ut.isEmptyOrNull(loginId)){
            return "아이디를 입력하세요";
        } else if (Ut.isEmptyOrNull(loginPw)) {
            return "비밀번호를 입력하세요";
        }else if (Ut.isEmptyOrNull(name)) {
            return "이름을 입력하세요";
        }else if (Ut.isEmptyOrNull(nickname)) {
            return "별명을 입력하세요";
        }else if (Ut.isEmptyOrNull(cellphoneNum)) {
            return "전화번호를 입력하세요";
        }else if (Ut.isEmptyOrNull(email)) {
            return "이메일을 입력하세요";
        }

        if (!loginPw.equals(loginPwChk)){
            return "비밀번호와 비밀번호 확인이 일치하지 않습니다.";
        }
        Member member = memberService.getMemberByLoginId(loginId);
        if(member!=null){
            return Ut.f("%s은(는) 중복된 아이디입니다.",loginId);
        }
        member = memberService.getMemberByNameAndEmail(name, email);
        if(member!=null){
            return  Ut.f("이름(%s)과 이메일(%s)이 같은 계정이 존재합니다.",name,email);
        }

        memberService.doJoin(loginId, loginPw,name,nickname,cellphoneNum,email);
        return Ut.f("%s님 가입 완료!",nickname);
    }
}
