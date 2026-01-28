package com.example.demo.vo;

import com.example.demo.util.Ut;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Scope(value="request",proxyMode= ScopedProxyMode.TARGET_CLASS)
public class Rq {

    @Getter
    private boolean isLogined;
    @Getter
    private int loginedMemberId;

    private HttpSession session;

    private HttpServletRequest req;
    private HttpServletResponse resp;

    public Rq(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
        this.session = req.getSession();

        if (session.getAttribute("loginedMemberId") != null) {
            isLogined = true;
            loginedMemberId = (int) session.getAttribute("loginedMemberId");
        }
        this.req.setAttribute("rq", this);
    }

    public void printHistoryBack(String msg) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");

        println("<script>");
        if (!Ut.isEmpty(msg)) {
            println("alert('" + msg + "');");
        }

        println("history.back();");

        println("</script>");
    }

    private void println(String str) throws IOException {
        print(str + "\n");
    }

    private void print(String str) throws IOException {
        resp.getWriter().append(str);
    }

    public void logout() {
        session.removeAttribute("loginedMemberId");
    }

    public void login(Member member) {
        session.setAttribute("loginedMemberId", member.getId());
    }

    public void initBeforeActionInterceptor() {
//        System.err.println("initBeforeActionInterceptor 실행됨");
    }
}