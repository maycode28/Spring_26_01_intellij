package com.example.demo.repository;


import com.example.demo.vo.Reaction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReactionRepository {

    public int getSumReactionPoint(int memberId, String relDataTypeCode, int relId);

    public int addLikePoint(int memberId, String relDataTypeCode, int relId);

    public int addDislikePoint(int memberId, String relDataTypeCode, int relId);

    public void deleteReactionPoint(int memberId, String relDataTypeCode, int relId);
}
