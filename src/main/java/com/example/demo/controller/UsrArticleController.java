package com.example.demo.controller;

import com.example.demo.service.ArticleService;
import com.example.demo.vo.Article;
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
    public Object getArticle(int id) {

        Article article = articleService.getArticleById(id);

        if (article == null) {
            return id + "번 글은 없음";
        }

        return article;
    }

    @RequestMapping("/usr/article/doAdd")
    @ResponseBody
    public String doAdd(String title, String body){
        Article article = articleService.writeArticle(title, body);
        return article.getId()+"번 글 작성 완료"+article;
    }

    @RequestMapping("/usr/article/getArticles")
    @ResponseBody
    public List<Article> getArticles(){

        return articleService.getArticles();
    }

    @RequestMapping("/usr/article/doDelete")
    @ResponseBody
    public String doDelete(int id){
        Article article = articleService.getArticleById(id);

        if (article == null) {
            return id+"번 글은 없습니다";
        }

        articleService.deleteArticle(id);
        return id+"번 글이 삭제 되었습니다";
    }

    @RequestMapping("/usr/article/doModify")
    @ResponseBody
    public String doModify(int id, String title, String body){

        Article article = articleService.getArticleById(id);

        if (article == null) {
            return id + "번 글은 없음";
        }

        articleService.modifyArticle(id, title, body);
        return id+"번 글이 수정 되었습니다"+article;
    }



}
