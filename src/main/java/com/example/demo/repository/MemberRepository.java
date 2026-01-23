package com.example.demo.repository;

import com.example.demo.vo.Member;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MemberRepository {
    Member getMemberByLoginId(String loginId);
    Member getMemberById(int id);
    void doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);
    Member getMemberByNameAndEmail(String name, String email);
    int getLastInsertId();
}
