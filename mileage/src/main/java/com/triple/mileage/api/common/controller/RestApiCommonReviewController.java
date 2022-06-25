package com.triple.mileage.api.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.triple.mileage.api.common.dto.ReviewParamDto;
import com.triple.mileage.api.common.entity.MemberEntity;
import com.triple.mileage.api.common.entity.PointHistoryEntity;
import com.triple.mileage.api.common.entity.ReviewEntity;
import com.triple.mileage.api.common.service.MemberService;
import com.triple.mileage.api.common.service.PointHistoryService;
import com.triple.mileage.api.common.service.ReviewService;

@RestController
public class RestApiCommonReviewController {
    
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private PointHistoryService pointHistoryService;

    @Autowired
    private MemberService memberService;

    @PostMapping(path = "/events")
    public Integer getCreateReview(
        HttpServletRequest request, @RequestBody ReviewParamDto reviewParamDto) {
                // HttpSession session = request.getSession();
                // String id = ""+session.getAttribute("id");
                // int afterId = Integer.parseInt(id);
                //이미 리뷰를 작성한 여행지인지 체크
                if(reviewService.duplicateCheckReview(reviewParamDto.getUserId(), reviewParamDto.getPlaceId()) != 0) {
                    return 0;
                }
                
                int point = 0;
                if(reviewParamDto.getAction().equals("ADD")) {
                    if(reviewParamDto.getContent().length() > 0) {
                        System.out.println("내용 있음 :" + reviewParamDto.getContent());
                        point++;
                    }
                    if(reviewParamDto.getAttachedPhotoIds().size() > 0) {
                        System.out.println("사진 있음 :" + reviewParamDto.getAttachedPhotoIds());
                        point++;
                    }
                    if(reviewService.checkFirstReview(reviewParamDto.getPlaceId()) == 0){
                        System.out.println("첫리뷰 :" + reviewService.checkFirstReview(reviewParamDto.getPlaceId()));
                        point++;
                    }
                    System.out.println("result proint ADD :" + point);
                    
                    MemberEntity userDetailData = memberService.getMemberDetail(Long.parseLong(reviewParamDto.getUserId()));
                    //포인트 지급
                    memberService.modifyMember(userDetailData, (long) point);
                    //포인트 히스토리 내역 넣기
                    PointHistoryEntity pointHistory = PointHistoryEntity.builder()
                    .userId(reviewParamDto.getUserId())
                    .pointValue(Long.valueOf(point))
                    .beforePoint(userDetailData.getPoint())
                    .afterPoint(userDetailData.getPoint()+point)
                    .build();
                    pointHistoryService.createPointHistory(pointHistory);

                } else if(reviewParamDto.getAction().equals("MOD")) {

                } else if(reviewParamDto.getAction().equals("DELETE")) {

                }
                System.out.println("reviewParamDto :" + reviewParamDto);
                ReviewEntity reviewEntity = ReviewEntity.builder()
                    .placeId(reviewParamDto.getPlaceId())
                    .userId(reviewParamDto.getUserId())
                    .attachedPhotoIds(""+reviewParamDto.getAttachedPhotoIds())
                    .content(reviewParamDto.getContent())
                    .build();
                System.out.println("reviewEntity :" + reviewEntity);
        return reviewService.createReview(reviewEntity);
    }

    // @PostMapping(path = "/account/login")
    // public MemberEntity logining(
    //         HttpServletRequest request,
    //         @RequestBody MemberDto memberDto) {
    //         MemberEntity memberEntity = MemberEntity.builder()
    //             .userId(memberDto.getUserId())
    //             .pw(memberDto.getPw())
    //             .build();
    //         System.out.println("memberEntity :" + memberEntity);
    //     return accountService.getMember(request, memberEntity);
    // }

}
