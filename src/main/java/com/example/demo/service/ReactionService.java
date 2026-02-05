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

    public ReactionService(ReactionRepository reactionRepository) {
        this.reactionRepository = reactionRepository;
    }


    public Reaction getReaction(int memberId, String relDataTypeCode, int relId) {
        return reactionRepository.getReaction(memberId, relDataTypeCode, relId);
    }

    public int getReactionCount(String relDataTypeCode, int relId) {
        return reactionRepository.getReactionCount(relDataTypeCode, relId);
    }

    public void initReaction(Reaction reaction) {
        reactionRepository.initReaction(reaction);
    }

    public void updateReaction(Reaction reaction) {
        reactionRepository.updateReaction(reaction);
    }
}
