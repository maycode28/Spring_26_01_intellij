package com.example.demo.controller;

import com.example.demo.service.ArticleService;
import com.example.demo.service.ReactionService;
import com.example.demo.vo.Reaction;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrReactionController {
    @Autowired
    private ReactionService reactionService;
    @Autowired
    private ArticleService articleService;

    @Autowired
    Rq rq;

    @RequestMapping("/usr/reaction/doLike")
    @ResponseBody
    public ResultData doLike(String relDataTypeCode, int relId, String replaceUri) {

        ResultData usersReactionRd = reactionService.usersReaction(rq.getLoginedMemberId(), relDataTypeCode, relId);

        int usersReaction = (int) usersReactionRd.getData1();

        if (usersReaction == 1) {
            ResultData rd = reactionService.deleteLikePoint(rq.getLoginedMemberId(), relDataTypeCode, relId);
            int goodRP = articleService.getLikePoint(relId);
            int badRP = articleService.getDislikePoint(relId);
            return ResultData.from("S-1", "좋아요 취소", "goodRP", goodRP, "badRP", badRP);
        } else if (usersReaction == -1) {
            ResultData rd = reactionService.deleteDislikePoint(rq.getLoginedMemberId(), relDataTypeCode, relId);
            rd = reactionService.addLikePoint(rq.getLoginedMemberId(), relDataTypeCode, relId);
            int goodRP = articleService.getLikePoint(relId);
            int badRP = articleService.getDislikePoint(relId);
            return ResultData.from("S-2", "싫어요 했었음", "goodRP", goodRP, "badRP", badRP);
        }

        ResultData reactionRd = reactionService.addLikePoint(rq.getLoginedMemberId(), relDataTypeCode, relId);

        if (reactionRd.isFail()) {
            return ResultData.from(reactionRd.getResultCode(), reactionRd.getMsg());
        }

        int goodRP = articleService.getLikePoint(relId);
        int badRP = articleService.getDislikePoint(relId);

        return ResultData.from(reactionRd.getResultCode(), reactionRd.getMsg(), "goodRP", goodRP, "badRP", badRP);
    }

    @RequestMapping("/usr/reaction/doDislike")
    @ResponseBody
    public ResultData  doDislike(String relDataTypeCode, int relId, String replaceUri) {

        ResultData usersReactionRd = reactionService.usersReaction(rq.getLoginedMemberId(), relDataTypeCode, relId);

        int usersReaction = (int) usersReactionRd.getData1();

        if (usersReaction == -1) {
            ResultData rd = reactionService.deleteDislikePoint(rq.getLoginedMemberId(), relDataTypeCode, relId);
            int goodRP = articleService.getLikePoint(relId);
            int badRP = articleService.getDislikePoint(relId);
            return ResultData.from("S-1", "싫어요 취소", "goodRP", goodRP, "badRP", badRP);
        } else if (usersReaction == 1) {
            ResultData rd = reactionService.deleteLikePoint(rq.getLoginedMemberId(), relDataTypeCode, relId);
            rd = reactionService.addDislikePoint(rq.getLoginedMemberId(), relDataTypeCode, relId);
            int goodRP = articleService.getLikePoint(relId);
            int badRP = articleService.getDislikePoint(relId);
            return ResultData.from("S-2", "좋아요 했었음", "goodRP", goodRP, "badRP", badRP);
        }

        ResultData reactionRd = reactionService.addDislikePoint(rq.getLoginedMemberId(), relDataTypeCode, relId);

        if (reactionRd.isFail()) {
            return ResultData.from(reactionRd.getResultCode(), reactionRd.getMsg());
        }

        int goodRP = articleService.getLikePoint(relId);
        int badRP = articleService.getDislikePoint(relId);

        return ResultData.from(reactionRd.getResultCode(), reactionRd.getMsg(), "goodRP", goodRP, "badRP", badRP);
    }

}

