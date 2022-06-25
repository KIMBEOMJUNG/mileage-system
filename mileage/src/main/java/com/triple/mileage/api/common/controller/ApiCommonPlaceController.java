package com.triple.mileage.api.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.triple.mileage.api.common.entity.PlaceEntity;
import com.triple.mileage.api.common.service.PlaceService;

@Controller
public class ApiCommonPlaceController {

    @Autowired
    private PlaceService placeService;
    
    @RequestMapping(path = "/place/list")
    public String getPlaceList() {
        return "/place/list";
    }

    @RequestMapping(path = "/place/detail")
    public String getPlacedetail(HttpServletRequest request, Model model) {
        System.out.println("request: "+request);
        System.out.println("request: "+request.getParameter("id"));
        Long id = Long.parseLong(request.getParameter("id")); 
        PlaceEntity placeDetail = placeService.getPlaceDetail(id);
        model.addAttribute("item", placeDetail);
        return "/place/detail";
    }
    
}
