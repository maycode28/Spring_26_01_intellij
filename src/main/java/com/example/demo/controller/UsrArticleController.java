package com.example.demo.controller;

import com.example.demo.service.ArticleService;
import com.example.demo.service.BoardService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.Board;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UsrArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private BoardService boardService;

    @Autowired
    Rq rq;

    @RequestMapping("/usr/article/detail")
    public String getArticle(Model model, int id) {

        Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
        if(article==null){
            ResultData rd = ResultData.from("F-1", "해당 글은 존재하지 않습니다.");
            model.addAttribute("rd", rd);
            model.addAttribute("action", "historyBack");
            return "/usr/common/error";
        }
        int memberId = rq.getLoginedMemberId();
        model.addAttribute("memberId",memberId);
        model.addAttribute("article", article);
        return "/usr/article/detail";
    }
    @RequestMapping("/usr/article/doIncreaseHitCountRd")
    @ResponseBody
    public ResultData doIncreaseHitCount(int id) {

        ResultData increaseHitCountRd = articleService.increaseHitCount(id);

        if (increaseHitCountRd.isFail()) {
            return increaseHitCountRd;
        }

        return ResultData.newData(increaseHitCountRd, "hitCount", articleService.getArticleHitCount(id));
    }

    @RequestMapping("/usr/article/write")
    public String write(Model model) {
        List<Board> boards = boardService.getBoards();
        model.addAttribute("boards", boards);
        return "/usr/article/write";
    }

    @RequestMapping("/usr/article/doWrite")
    @ResponseBody
    public String doWrite(String title, String body, String board) {
        if (Ut.isEmptyOrNull(title)) {
            return Ut.jsHistoryBack("F-1", "제목써");
        }
        if (Ut.isEmptyOrNull(body)) {
            return Ut.jsHistoryBack("F-2", "내용써");
        }
        if (Ut.isEmptyOrNull(board)) {
            return Ut.jsHistoryBack("F-3", "게시판 선택해");
        }
        int memberId = rq.getLoginedMemberId();
        int boardId = Integer.parseInt(board);
        ResultData<Integer> writeArticleRd = articleService.writeArticle(title, body, memberId, boardId);
        return Ut.jsReplace(writeArticleRd.getResultCode(), writeArticleRd.getMsg(), Ut.f("../article/detail?id=%d", writeArticleRd.getData1()));
    }


    @RequestMapping("/usr/article/list")
    public String showList(Model model, String boardId, String page, String searchBy, String keyword) {
        Board board = boardService.getBoardById(boardId);
        List<Board> boards = boardService.getBoards();
        model.addAttribute("boards", boards);
        String boardName = "전체 게시판";
        List<Article> articles = null;
        int articleCount = 0;
        int totalPage = 1;
        int cPage = 1;
        int articlesPerPage = 10;
        int boardIdInt = 0;
        if (page != null) {
            cPage = Integer.parseInt(page);
        }
        model.addAttribute("cPage", cPage);
        if (boardId != null && !boardId.isBlank()) {
            if (board == null) {
                ResultData rd = ResultData.from("F-1", "존재하지 않는 게시판 입니다.");
                model.addAttribute("rd", rd);
                model.addAttribute("action", "historyBack");
                return "/usr/common/error";
            }
            boardIdInt = Integer.parseInt(boardId);
            boardName = board.getName();
        }

        articles = articleService.getForPirntArticles(boardIdInt, cPage, articlesPerPage, searchBy, keyword);
        articleCount = articleService.getForPrintArticleCount(boardIdInt, searchBy, keyword);
        if (articleCount != 0) {
            totalPage = (int) Math.ceil(articleCount / (double) articlesPerPage);
        }
        if (cPage != 1 && cPage > totalPage) {
            ResultData rd = ResultData.from("F-2", "존재하지 않는 페이지 입니다.");
            model.addAttribute("rd", rd);
            model.addAttribute("action", "historyBack");
            return "/usr/common/error";
        }
        model.addAttribute("articles", articles);
        model.addAttribute("boardName", boardName);
        model.addAttribute("totalPage", totalPage);
        return "/usr/article/list";
    }


    @RequestMapping("/usr/article/doDelete")
    @ResponseBody
    public String doDelete(HttpServletRequest req, int id) {
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
    public String modify(HttpServletRequest req, Model model, int id) {
        Rq rq = (Rq) req.getAttribute("rq");

        Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

        if (article == null) {
            ResultData rd = ResultData.from("F-1", Ut.f("%d번 게시글은 존재하지 않습니다.", id));
            model.addAttribute("rd", rd);
            model.addAttribute("action", "historyBack");
            return "/usr/common/error";
        }
        ResultData userCanModifyRd = articleService.userCanModify(rq.getLoginedMemberId(), article);
        System.out.println(userCanModifyRd.isFail());
        if (userCanModifyRd.isFail()) {
            model.addAttribute("rd", userCanModifyRd);
            model.addAttribute("action", "historyBack");
            return "/usr/common/error";
        }
        model.addAttribute("article", article);
        return "/usr/article/modify";
    }

    @RequestMapping("/usr/article/doModify")
    @ResponseBody
    public String doModify(int id, String title, String body) {
        articleService.modifyArticle(id, title, body);

        return Ut.jsReplace("S-1", Ut.f("%d번 게시글이 수정되었습니다.", id), "../article/list");
    }
}
