package com.example.demo.controller;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UsrArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/usr/article/getArticle")
    @ResponseBody
    public ResultData<Article> getArticle(int id) {

        Article article = articleService.getArticleById(id);

        if (article == null) {
            return ResultData.from("F-1", Ut.f("%d번 게시글은 없음", id));
        }

        return ResultData.from("S-1", Ut.f("%d번 게시글입니다.", id), article);
    }

    @RequestMapping("/usr/article/doWrite")
    @ResponseBody
    public ResultData<Article> doWrite(String title, String body){
        if (Ut.isEmptyOrNull(title)) {
            return ResultData.from("F-1", "제목써");
        }
        if (Ut.isEmptyOrNull(body)) {
            return ResultData.from("F-2", "내용써");
        }
        ResultData<Integer> writeArticleRd = articleService.writeArticle(title, body);
        Article article = articleService.getArticleById(writeArticleRd.getData1());
        return ResultData.from(writeArticleRd.getResultCode(), writeArticleRd.getMsg(), article);
    }


    @RequestMapping("/usr/article/getArticles")
    @ResponseBody
    public ResultData<List<Article>> getArticles(){

        return ResultData.from("S-1", "전체 게시글입니다.",articleService.getArticles());
    }

    @RequestMapping("/usr/article/doDelete")
    @ResponseBody
    public ResultData<Integer> doDelete(int id){
        Article article = articleService.getArticleById(id);

        if (article == null) {
            return ResultData.from("F-1", Ut.f("%d번 게시글은 없어", id));
        }

        articleService.deleteArticle(id);
        return ResultData.from("S-1", Ut.f("%d번 게시글이 삭제됨", id), id);
    }

    @RequestMapping("/usr/article/doModify")
    @ResponseBody
    public ResultData<Article> doModify (int id, String title, String body) {

        Article article = articleService.getArticleById(id);

        if (article == null) {
            return ResultData.from("F-1", Ut.f("%d번 게시글은 없어", id));
        }

        articleService.modifyArticle(id, title, body);
        article = articleService.getArticleById(id);
        return ResultData.from("S-1", Ut.f("%d번 게시글이 수정됨", id), article);

    }

}
