package com.triple.mileage.api.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApiCommonLikedController {
    
    @RequestMapping(path = "/mypage/likedList")
    public String getLikedList() {
        return "/mypage/likedList";
    }
    
}
