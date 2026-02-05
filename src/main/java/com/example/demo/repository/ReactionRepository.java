package com.example.demo.repository;


import com.example.demo.vo.Reaction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReactionRepository {

    public Reaction getReaction(int memberId, String relDataTypeCode, int relId);
    public int getReactionCount(String relDataTypeCode, int relId);
    public void initReaction(Reaction reaction);
    public void updateReaction(Reaction reaction);
    public int getCurrentReactionStatus(int memberId, String relDataTypeCode, int relId);
}
