package com.example.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UsrHomeController {
    int num;
    public UsrHomeController(){
        num=0;
    }
    @RequestMapping("/usr/home/main")
    @ResponseBody
    public String showMain(){
        return "안녕하세요";
    }
    @RequestMapping("/usr/home/main2")
    @ResponseBody
    public String showMain2(){
        return "잘가";
    }
    @RequestMapping("/usr/home/main3")
    @ResponseBody
    public int showMain3(){
        int a = 1;
        int b = 2;
        return a + b;
    }

    @RequestMapping("/usr/home/getNum")
    @ResponseBody
    public int getNum(){
        return num++;
    }

    @RequestMapping("/usr/home/setNum")
    @ResponseBody
    public String setNum(){
        num=0;
        return "num 값 0으로 초기화";
    }

    @RequestMapping("/usr/home/setNumValue")
    @ResponseBody
    public String setNumValue(int value){
        this.num=value;
        return "num 값 "+value+"(으)로 초기화";
    }

    @RequestMapping("/usr/home/getInt")
    @ResponseBody
    public int getInt(){
        return 5;
    }

    @RequestMapping("/usr/home/getString")
    @ResponseBody
    public String getString(){
        return "5";
    }

    @RequestMapping("/usr/home/getMap")
    @ResponseBody
    public Map<String, String> getMap(){
        Map<String, String> map = new HashMap<String, String>() {
            {
                put("key1", "value1");
                put("key2", "value2");
            }
        };
        return map;
    }

    @RequestMapping("/usr/home/getDouble")
    @ResponseBody
    public double getDouble(){
        return 5.23;
    }

    @RequestMapping("/usr/home/getBoolean")
    @ResponseBody
    public boolean getBoolean(){
        return true;
    }

    @RequestMapping("/usr/home/getList")
    @ResponseBody
    public List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("bye");
        return list;
    }

    @RequestMapping("/usr/home/getArticle")
    @ResponseBody
    public Article getArticle(){
        Article article = new Article (1,"hello","bye");
        return article;
    }


}
