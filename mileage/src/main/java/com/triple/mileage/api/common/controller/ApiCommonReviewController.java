package com.triple.mileage.api.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.triple.mileage.api.common.entity.ReviewEntity;
import com.triple.mileage.api.common.service.ReviewService;

@Controller
public class ApiCommonReviewController {

    @Autowired
    private ReviewService reviewService;

    //리뷰 쓰기 페이지 컨트롤러
    @RequestMapping(path = "/review/write")
    public String getReviewWrite(HttpServletRequest request, Model model) {
        Long placeId = Long.parseLong(request.getParameter("id"));
        model.addAttribute("placeId", placeId);
        return "/review/write";
    }

    //리뷰 수정 페이지 컨트롤러
    @RequestMapping(path = "/review/update")
    public String getReviewUpdate(HttpServletRequest request, Model model) {
        Long reviewId = Long.parseLong(request.getParameter("id"));
        ReviewEntity reviewEntity = reviewService.getReviewDetail(reviewId);
        model.addAttribute("item", reviewEntity);
        return "/review/update";
    }
    
}
