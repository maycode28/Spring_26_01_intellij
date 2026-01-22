package com.example.demo.repository;

import com.example.demo.vo.Member;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MemberRepository {
    Member getMemberByLoginId(String loginId);
    void doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);
    Member getMemberByEmail(String email);
}
