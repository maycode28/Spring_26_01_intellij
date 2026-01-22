package com.example.demo.service;


import com.example.demo.repository.MemberRepository;
import com.example.demo.vo.Member;
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

    public void doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
        memberRepository.doJoin(loginId,loginPw,name,nickname,cellphoneNum,email);
    }

    public Member getMemberByEmail(String email) {
        return memberRepository.getMemberByEmail(email);
    }
}
