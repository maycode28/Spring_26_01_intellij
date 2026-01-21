package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
