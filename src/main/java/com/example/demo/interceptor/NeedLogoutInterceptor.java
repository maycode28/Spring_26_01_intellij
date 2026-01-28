package com.example.demo.interceptor;

import com.example.demo.vo.Rq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class NeedLogoutInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object Handler) throws Exception {

        Rq rq = (Rq) req.getAttribute("rq");

        if (rq.isLogined()) {

            rq.printHistoryBack("로그아웃 하고 사용해야함");

            return false;
        }

        return HandlerInterceptor.super.preHandle(req, resp, Handler);
    }
}