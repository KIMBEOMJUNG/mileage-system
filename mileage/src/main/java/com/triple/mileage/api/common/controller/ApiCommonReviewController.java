package com.triple.mileage.api.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.triple.mileage.api.common.service.PlaceService;

@Controller
public class ApiCommonReviewController {

    @Autowired
    private PlaceService placeService;

    @RequestMapping(path = "/review/write")
    public String getReviewWrite(HttpServletRequest request, Model model) {
        Long placeId = Long.parseLong(request.getParameter("id"));
        model.addAttribute("placeId", placeId);
        return "/review/write";
    }
    
}
