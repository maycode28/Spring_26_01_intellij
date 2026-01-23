package com.example.demo.controller;

import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrMemberController {
    @Autowired
    private MemberService memberService;


    @RequestMapping("/usr/member/doLogin")
    @ResponseBody
    public ResultData<Member> doLogin(HttpSession session, String loginId, String loginPw) {

        boolean isLogined = false;

        if (session.getAttribute("loginedMemberId") != null) {
            isLogined = true;
        }

        if (isLogined) {
            return ResultData.from("F-A", "이미 로그인중");
        }

        if (Ut.isEmptyOrNull(loginId)) {
            return ResultData.from("F-1", "loginId 입력해");
        }
        if (Ut.isEmptyOrNull(loginPw)) {
            return ResultData.from("F-2", "loginPw 입력해");
        }

        Member member = memberService.getMemberByLoginId(loginId);

        if (member == null) {
            return ResultData.from("F-3", Ut.f("%s는 없는 아이디", loginId));
        }

        if (member.getLoginPw().equals(loginPw) == false) {
            return ResultData.from("F-4", "비밀번호 틀렸음");
        }

        session.setAttribute("loginedMemberId", member.getId());

        return ResultData.from("S-1", Ut.f("%s님 환영합니다", member.getNickname()), member);
    }

    @RequestMapping("/usr/member/doLogout")
    @ResponseBody
    public ResultData<Member> doLogout(HttpSession session) {
        boolean isLogined = false;

        if (session.getAttribute("loginedMemberId") != null) {
            isLogined = true;
        }

        if (!isLogined) {
            return ResultData.from("F-A", "이미 로그아웃 중");
        }
        session.invalidate();
        return ResultData.from("S-1", "로그아웃 되었습니다.");

    }

    @RequestMapping("/usr/member/doJoin")
    @ResponseBody
    public ResultData<Member> doJoin(HttpSession session, String loginId, String loginPw, String loginPwChk, String name, String nickname, String cellphoneNum, String email){

        boolean isLogined = false;

        if (session.getAttribute("loginedMemberId") != null) {
            isLogined = true;
        }

        if (isLogined) {
            return ResultData.from("F-A", "이미 로그인중");
        }

        if(Ut.isEmptyOrNull(loginId)){
            return ResultData.from("F-1", "아이디를 입력하세요");
        } else if (Ut.isEmptyOrNull(loginPw)) {
            return ResultData.from("F-2", "비밀번호를 입력하세요");
        }else if (Ut.isEmptyOrNull(name)) {
            return ResultData.from("F-3", "이름을 입력하세요");
        }else if (Ut.isEmptyOrNull(nickname)) {
            return ResultData.from("F-4", "별명을 입력하세요");
        }else if (Ut.isEmptyOrNull(cellphoneNum)) {
            return ResultData.from("F-5", "전화번호를 입력하세요");
        }else if (Ut.isEmptyOrNull(email)) {
            return ResultData.from("F-6", "이메일을 입력하세요");
        }

        if (!loginPw.equals(loginPwChk)){
            return ResultData.from("F-8", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        Member member = memberService.getMemberByLoginId(loginId);
        if(member!=null){
            return ResultData.from("F-7", Ut.f("이미 사용중인 아이디(%s) 입니다", loginId));
        }
        member = memberService.getMemberByNameAndEmail(name, email);
        if(member!=null){
            return ResultData.from("F-8", Ut.f("이미 사용중인 이름(%s)과 이메일(%s) 입니다", name, email));        }

        return memberService.doJoin(loginId, loginPw,name,nickname,cellphoneNum,email);


    }
}
