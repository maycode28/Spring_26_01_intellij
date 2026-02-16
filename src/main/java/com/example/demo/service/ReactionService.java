package com.example.demo.service;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.ReactionRepository;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.Reaction;
import com.example.demo.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;
    @Autowired
    private ArticleService articleService;

    public ReactionService(ReactionRepository reactionRepository) {
        this.reactionRepository = reactionRepository;
    }

    public ResultData usersReaction(int loginedMemberId, String relTypeCode, int relId) {

        if (loginedMemberId == 0) {
            return ResultData.from("F-L", "로그인 하고 써야해");
        }

        int sumReactionPointByMemberId = reactionRepository.getSumReactionPoint(loginedMemberId, relTypeCode,
                relId);

        if (sumReactionPointByMemberId != 0) {
            return ResultData.from("F-1", "추천 불가능", "sumReactionPointByMemberId", sumReactionPointByMemberId);
        }

        return ResultData.from("S-1", "추천 가능", "sumReactionPointByMemberId", sumReactionPointByMemberId);
    }

    public ResultData addLikePoint(int loginedMemberId, String relDataTypeCode, int relId) {

        int affectedRow = reactionRepository.addLikePoint(loginedMemberId, relDataTypeCode, relId);

        if (affectedRow != 1) {
            return ResultData.from("F-1", "좋아요 실패");
        }

        switch (relDataTypeCode) {
            case "article":
                articleService.increaseLikePoint(relId);
                break;
        }

        return ResultData.from("S-1", "좋아요!");
    }

    public ResultData addDislikePoint(int loginedMemberId, String relDataTypeCode, int relId) {
        int affectedRow = reactionRepository.addDislikePoint(loginedMemberId, relDataTypeCode, relId);

        if (affectedRow != 1) {
            return ResultData.from("F-1", "싫어요 실패");
        }

        switch (relDataTypeCode) {
            case "article":
                articleService.increaseDislikePoint(relId);
                break;
        }

        return ResultData.from("S-1", "싫어요!");
    }

    public ResultData deleteLikePoint(int loginedMemberId, String relDataTypeCode, int relId) {
        reactionRepository.deleteReactionPoint(loginedMemberId, relDataTypeCode, relId);

        switch (relDataTypeCode) {
            case "article":
                articleService.decreaseLikePoint(relId);
                break;
        }
        return ResultData.from("S-1", "좋아요 취소 됨");

    }

    public ResultData deleteDislikePoint(int loginedMemberId, String relDataTypeCode, int relId) {
        reactionRepository.deleteReactionPoint(loginedMemberId, relDataTypeCode, relId);

        switch (relDataTypeCode) {
            case "article":
                articleService.decreaseDislikePoint(relId);
                break;
        }
        return ResultData.from("S-1", "싫어요 취소 됨");
    }

    public boolean isAlreadyAddGoodRp(int memberId, int relId, String relDataTypeCode) {
        int getPointTypeCodeByMemberId = reactionRepository.getSumReactionPoint(memberId, relDataTypeCode, relId);

        if (getPointTypeCodeByMemberId > 0) {
            return true;
        }

        return false;
    }

    public boolean isAlreadyAddBadRp(int memberId, int relId, String relDataTypeCode) {
        int getPointTypeCodeByMemberId = reactionRepository.getSumReactionPoint(memberId, relDataTypeCode, relId);

        if (getPointTypeCodeByMemberId < 0) {
            return true;
        }

        return false;
    }

}
