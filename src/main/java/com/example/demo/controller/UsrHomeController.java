package com.example.demo.controller;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UsrHomeController {
    @RequestMapping("/usr/home/main")
    public String showMain(){
        return "/usr/home/main";
    }

    @RequestMapping("/")
    public String showMain2(){
        return "redirect:/usr/home/main";
    }


}
