package com.example.demo.service;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ResultData<Integer> writeArticle(String title, String body, int memberId, int boardId) {
        articleRepository.writeArticle(title, body, memberId, boardId);
        int id = articleRepository.getLastInsertId();
        return ResultData.from("S-1", Ut.f("%d번 게시글 작성", id),"id", id);
    }

    public void deleteArticle(int id) {
        articleRepository.deleteArticle(id);
    }

    public void modifyArticle(int id, String title, String body) {
        articleRepository.modifyArticle(id, title, body);

    }

    public Article getArticleById(int id) {
        return articleRepository.getArticleById(id);
    }


    public List<Article> getForPirntArticles(int boardId, int cPage, int articlesPerPage, String searchBy, String keyword) {
        int startsFrom = cPage * 10 - 10;
        return articleRepository.getForPirntArticles(boardId, startsFrom, articlesPerPage, searchBy, keyword);
    }

    public int getForPrintArticleCount(int boardId, String searchBy, String keyword) {
        return articleRepository.getForPrintArticleCount(boardId, searchBy, keyword);
    }

    public Article getForPrintArticle(int loginedMemberId, int id) {

        Article article = articleRepository.getForPrintArticle(id);

        controlForPrintData(loginedMemberId, article);

        return article;
    }

    private void controlForPrintData(int loginedMemberId, Article article) {
        if (article == null) {
            return;
        }

        ResultData userCanModifyRd = userCanModify(loginedMemberId, article);
        article.setUserCanModify(userCanModifyRd.isSuccess());
        ResultData userCanDeleteRd = userCanDelete(loginedMemberId, article);
        article.setUserCanDelete(userCanDeleteRd.isSuccess());
    }

    public ResultData userCanModify(int loginedMemberId, Article article) {

        if (article.getMemberId() != loginedMemberId) {
            return ResultData.from("F-A2", Ut.f("%d번 게시글에 대한 권한 없음", article.getId()));
        }

        return ResultData.from("S-1", Ut.f("%d번 게시글 수정이 가능합니다.", article.getId()));
    }

    public ResultData userCanDelete(int loginedMemberId, Article article) {
        if (article.getMemberId() != loginedMemberId) {
            return ResultData.from("F-A2", Ut.f("%d번 게시글에 대한 삭제 권한 없음", article.getId()));
        }

        return ResultData.from("S-1", Ut.f("%d번 게시글이 삭제되었습니다.", article.getId()));
    }

    public ResultData increaseHitCount(int id) {
        int affectedRow = articleRepository.increaseHitCount(id);
        if (affectedRow == 0) {
            return ResultData.from("F-1", "해당 게시글은 없음", "id", id);
        }
        return ResultData.from("S-1", "조회수 증가", "id", id);
    }
}
