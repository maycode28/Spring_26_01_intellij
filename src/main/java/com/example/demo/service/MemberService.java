package com.example.demo.service;


import com.example.demo.repository.MemberRepository;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }

    public Member getMemberByLoginId(String loginId) {
        return memberRepository.getMemberByLoginId(loginId);
    }

    public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
        memberRepository.doJoin(loginId,loginPw,name,nickname,cellphoneNum,email);
        int id = memberRepository.getLastInsertId();
        Member member = memberRepository.getMemberById(id);
        return ResultData.from("S-1", "회원가입 성공","member", member);
    }

    public Member getMemberByNameAndEmail(String name, String email) {
        return memberRepository.getMemberByNameAndEmail(name, email);
    }
}
