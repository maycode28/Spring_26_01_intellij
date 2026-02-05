package com.example.demo.controller;

import com.example.demo.service.ReactionService;
import com.example.demo.vo.Reaction;
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
    Rq rq;

    @RequestMapping("/usr/reaction/updateReaction")
    @ResponseBody
    public void updateReaction(int memberId, String relDataTypeCode, int relId, int reactionStatus) {
        Reaction reaction = reactionService.getReaction(memberId, relDataTypeCode, relId);

        if (reaction == null) {
            reaction = new Reaction(memberId, relDataTypeCode, relId, reactionStatus);
            reactionService.initReaction(reaction);
        } else {
            reaction.setReactionStatus(reactionStatus);
            reactionService.updateReaction(reaction);
        }

    }

    @RequestMapping("/usr/reaction/getReactionCount")
    @ResponseBody
    public int getReactionCount(String relDataTypeCode, int relId) {
        return reactionService.getReactionCount(relDataTypeCode, relId);

    }
}

