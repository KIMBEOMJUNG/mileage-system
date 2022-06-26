package com.triple.mileage.api.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.triple.mileage.api.common.dto.ReviewParamDto;
import com.triple.mileage.api.common.entity.PlaceEntity;
import com.triple.mileage.api.common.service.PlaceService;
import com.triple.mileage.api.common.service.ReviewService;
import com.triple.mileage.common.util.PageRequest;

@Controller
public class ApiCommonPlaceController {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private ReviewService reviewService;
    
    //여행지 리스트 페이지 컨트롤러
    @RequestMapping(path = "/place/list")
    public String getPlaceList() {
        return "place/list";
    }

    //여행지 상세 페이지 컨트롤러
    @RequestMapping(path = "/place/detail")
    public String getPlacedetail(HttpServletRequest request, Model model) {
        Long id = Long.parseLong(request.getParameter("id")); 
        PlaceEntity placeDetail = placeService.getPlaceDetail(id);
        model.addAttribute("item", placeDetail);
        ReviewParamDto reviewParamDto = new ReviewParamDto();
        reviewParamDto.setPlaceId(""+id);
        PageRequest pageRequest = new PageRequest();
        pageRequest.setRowSize(99999);
        model.addAttribute("reviewList", reviewService.getReviewList(reviewParamDto, pageRequest));
        return "place/detail";
    }
    
}
