package com.triple.mileage.api.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.triple.mileage.api.common.dto.ReviewParamDto;
import com.triple.mileage.api.common.entity.MemberEntity;
import com.triple.mileage.api.common.service.MemberService;
import com.triple.mileage.api.common.service.ReviewService;

@RestController
public class RestApiCommonReviewController {
    
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MemberService memberService;

    //리뷰 이벤트 컨트롤러
    @PostMapping(path = "/events")
    public Integer getCreateReview(
        HttpServletRequest request, @RequestBody ReviewParamDto reviewParamDto) {
                HttpSession session = request.getSession();
                MemberEntity userDetailData = memberService.getMemberDetail(Long.parseLong(reviewParamDto.getUserId()));
                if(reviewParamDto.getAction().equals("ADD")) {
                    //이미 리뷰를 작성한 여행지인지 체크
                    if(reviewService.duplicateCheckReview(reviewParamDto.getUserId(), reviewParamDto.getPlaceId()) != 0) {
                        return 0;
                    }
                    //리뷰 추가 시
                    session.setAttribute("point", reviewService.createReview(reviewParamDto, userDetailData));
                } else if(reviewParamDto.getAction().equals("MOD")) {
                    //리뷰 수정 시
                    session.setAttribute("point", reviewService.modifyReview(reviewParamDto, userDetailData));
                } else if(reviewParamDto.getAction().equals("DELETE")) {
                    //리뷰 삭제 시
                    session.setAttribute("point", reviewService.removeReview(reviewParamDto, userDetailData));
                }
        return 1;
    }

}
