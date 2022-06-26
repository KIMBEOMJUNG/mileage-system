package com.triple.mileage.api.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApiCommonAccountController {

    //로그인 페이지 컨트롤러
    @RequestMapping(path = "/login")
    public String getLogin() {
        return "/account/login";
    }

    //로그아웃 컨트롤러
    @RequestMapping(path = "/logout")
    public String getLogout(
        HttpServletRequest request) {
            HttpSession session = request.getSession();
            session.invalidate();
        return "/account/login";
    }
    
}
