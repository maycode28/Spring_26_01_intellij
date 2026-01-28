package com.example.demo.controller;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;
import jakarta.servlet.http.HttpServletRequest;
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

    @Autowired
    Rq rq;

    @RequestMapping("/usr/article/detail")
    public String getArticle( Model model, int id) {

        Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
        model.addAttribute("article", article);
        return "/usr/article/detail";
    }
    @RequestMapping("/usr/article/write")
    public String write () {
        return "/usr/article/write";
    }
    @RequestMapping("/usr/article/doWrite")
    @ResponseBody
    public String doWrite(String title, String body){
        if (Ut.isEmptyOrNull(title)) {
            return Ut.jsHistoryBack("F-1", "제목써");
        }
        if (Ut.isEmptyOrNull(body)) {
            return Ut.jsHistoryBack("F-2", "내용써");
        }
        int memberId = rq.getLoginedMemberId();
        ResultData<Integer> writeArticleRd = articleService.writeArticle(title, body, memberId);
        return Ut.jsReplace(writeArticleRd.getResultCode(), writeArticleRd.getMsg(), Ut.f("../article/detail?id=%d",writeArticleRd.getData1()));
    }


    @RequestMapping("/usr/article/list")
    public String showList(Model model){
        List<Article> articles =articleService.getArticles();
        model.addAttribute("articles", articles);
        return "/usr/article/list";
    }


    @RequestMapping("/usr/article/doDelete")
    @ResponseBody
    public String doDelete(HttpServletRequest req, int id){
        Rq rq = (Rq) req.getAttribute("rq");

        Article article = articleService.getArticleById(id);

        if (article == null) {
            return Ut.jsHistoryBack("F-1", Ut.f("%d번 게시글은 존재하지 않습니다.", id));
        }
        ResultData userCanDeleteRd = articleService.userCanDelete(rq.getLoginedMemberId(), article);
        if (userCanDeleteRd.isFail()) {
            return Ut.jsHistoryBack(userCanDeleteRd.getResultCode(), userCanDeleteRd.getMsg());
        }

        if (userCanDeleteRd.isSuccess()) {
            articleService.deleteArticle(id);
        }
        return Ut.jsReplace(userCanDeleteRd.getResultCode(), userCanDeleteRd.getMsg(), "../article/list");
    }

    @RequestMapping("/usr/article/modify")
    public String modify (HttpServletRequest req, Model model, int id) {
        Rq rq = (Rq) req.getAttribute("rq");

        Article article = articleService.getArticleById(id);

        if (article == null) {
            return Ut.jsHistoryBack("F-1", Ut.f("%d번 게시글은 존재하지 않습니다.", id));
        }
        ResultData userCanModifyRd = articleService.userCanModify(rq.getLoginedMemberId(), article);
        System.out.println(userCanModifyRd.isFail());
        if (userCanModifyRd.isFail()) {
            return Ut.jsHistoryBack(userCanModifyRd.getResultCode(), userCanModifyRd.getMsg());
        }
        model.addAttribute("article", article);
        return "/usr/article/modify";
    }

    @RequestMapping("/usr/article/doModify")
    @ResponseBody
    public String doModify (int id, String title, String body) {
        articleService.modifyArticle(id, title, body);

        return Ut.jsReplace("S-1", Ut.f("%d번 게시글이 수정되었습니다.", id), "../article/list");
    }
}
