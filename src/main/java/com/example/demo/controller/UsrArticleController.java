package com.example.demo.controller;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping("/usr/article/detail")
    public String getArticle(Model model, int id) {

        Article article = articleService.getArticleById(id);

        if (article == null) {
            return  Ut.f("%d번 게시글은 없음", id);
        }
        model.addAttribute("article", article);
        return "/usr/article/detail";
    }

    @RequestMapping("/usr/article/doWrite")
    @ResponseBody
    public ResultData<Article> doWrite(HttpSession session, String title, String body){
        boolean isLogined = false;

        if (session.getAttribute("loginedMemberId") != null) {
            isLogined = true;
        }

        if (!isLogined) {
            return ResultData.from("F-A", "로그인이 필요합니다.");
        }

        if (Ut.isEmptyOrNull(title)) {
            return ResultData.from("F-1", "제목써");
        }
        if (Ut.isEmptyOrNull(body)) {
            return ResultData.from("F-2", "내용써");
        }
        int memberId = (int)session.getAttribute("loginedMemberId");
        ResultData<Integer> writeArticleRd = articleService.writeArticle(title, body, memberId);
        Article article = articleService.getArticleById(writeArticleRd.getData1());
        return ResultData.from(writeArticleRd.getResultCode(), writeArticleRd.getMsg(), article);
    }


    @RequestMapping("/usr/article/list")
    public String showList(Model model){
        List<Article> articles =articleService.getArticles();
        model.addAttribute("articles", articles);
        return "/usr/article/list";
    }


    @RequestMapping("/usr/article/doDelete")
    @ResponseBody
    public ResultData<Integer> doDelete(HttpSession session, int id){
        boolean isLogined = false;

        if (session.getAttribute("loginedMemberId") != null) {
            isLogined = true;
        }

        if (!isLogined) {
            return ResultData.from("F-A", "로그인이 필요합니다.");
        }
        int loginedMemberId=(int)session.getAttribute("loginedMemberId");

        Article article = articleService.getArticleById(id);

        if (article == null) {
            return ResultData.from("F-1", Ut.f("%d번 게시글은 없어", id));
        }else if(loginedMemberId!=article.getMemberId()){
            return ResultData.from("F-A2", "글의 작성자만 삭제할 수 있습니다.");
        }

        articleService.deleteArticle(id);
        return ResultData.from("S-1", Ut.f("%d번 게시글이 삭제됨", id), id);
    }

    @RequestMapping("/usr/article/doModify")
    @ResponseBody
    public ResultData<Article> doModify (HttpSession session, int id, String title, String body) {
        boolean isLogined = false;

        if (session.getAttribute("loginedMemberId") != null) {
            isLogined = true;
        }

        if (!isLogined) {
            return ResultData.from("F-A", "로그인이 필요합니다.");
        }
        int loginedMemberId=(int)session.getAttribute("loginedMemberId");

        Article article = articleService.getArticleById(id);

        if (article == null) {
            return ResultData.from("F-1", Ut.f("%d번 게시글은 없어", id));
        }else if(loginedMemberId!=article.getMemberId()){
            return ResultData.from("F-A2", "글의 작성자만 수정할 수 있습니다.");
        }

        articleService.modifyArticle(id, title, body);
        article = articleService.getArticleById(id);
        return ResultData.from("S-1", Ut.f("%d번 게시글이 수정됨", id), article);

    }

}
