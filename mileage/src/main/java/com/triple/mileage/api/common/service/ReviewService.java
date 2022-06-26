package com.triple.mileage.api.common.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.triple.mileage.api.common.dto.ReviewParamDto;
import com.triple.mileage.api.common.entity.MemberEntity;
import com.triple.mileage.api.common.entity.PointHistoryEntity;
import com.triple.mileage.api.common.entity.ReviewEntity;
import com.triple.mileage.api.common.mapper.ReviewMapper;
import com.triple.mileage.common.util.PageRequest;
import com.triple.mileage.common.util.util;
import com.triple.mileage.common.validation.group.CreateValidationGroup;
import com.triple.mileage.common.validation.group.ModifyValidationGroup;
import com.triple.mileage.common.validation.group.ReadValidationGroup;
import com.triple.mileage.common.validation.group.RemoveValidationGroup;

@Validated
@Service
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private MemberService memberService;

    @Autowired
    private PointHistoryService pointHistoryService;

    @Validated(value = { CreateValidationGroup.class })
    @Transactional
    public Integer createReview( //리뷰 추가
        @Valid @NotNull (groups = {RemoveValidationGroup.class}) ReviewParamDto reviewParamDto,
        @Valid @NotNull (groups = {RemoveValidationGroup.class}) MemberEntity memberEntity) {
                // 지급 포인트 체크
                int point = getReviewBouse(reviewParamDto);
                //첫 리뷰 확인
                if(checkFirstReview(reviewParamDto.getPlaceId()) == 0){ //첫리뷰 확인
                    reviewParamDto.setFirstState(1);
                } else {
                    reviewParamDto.setFirstState(0);
                }
                //리뷰 DB 추가
                ReviewEntity reviewEntity = ReviewEntity.builder()
                    .placeId(reviewParamDto.getPlaceId())
                    .userId(reviewParamDto.getUserId())
                    .attachedPhotoIds(""+reviewParamDto.getAttachedPhotoIds())
                    .content(reviewParamDto.getContent())
                    .firstState(reviewParamDto.getFirstState())
                    .build();
                reviewMapper.insertReview(reviewEntity);
                //포인트 지급 히스토리 추가
                PointHistoryEntity pointHistory = PointHistoryEntity.builder()
                .userId(reviewParamDto.getUserId())
                .pointValue(Long.valueOf(point))
                .beforePoint(memberEntity.getPoint())
                .afterPoint(memberEntity.getPoint()+point)
                .build();
                pointHistoryService.createPointHistory(pointHistory);
                //포인트 지급
                memberService.modifyMember(memberEntity, (long) point);
        return point;
    }

    @Validated (value = {ModifyValidationGroup.class})
    @Transactional
    public Integer modifyReview( //리뷰 수정
        @Valid @NotNull (groups = {RemoveValidationGroup.class}) ReviewParamDto reviewParamDto,
        @Valid @NotNull (groups = {RemoveValidationGroup.class}) MemberEntity memberEntity) {
        //수정전 리뷰의 지급 포인트 확인
        ReviewEntity befoReviewEntity = reviewMapper.selectReview(reviewParamDto.getReviewId());
        ReviewParamDto beforReviewParamDto = new ReviewParamDto();
        beforReviewParamDto.setAction("MOD");

        //수정 전 리뷰의 지급된 포인트 확인
        ArrayList<String> arrayListPhoto = util.stringToArrayList(befoReviewEntity.getAttachedPhotoIds());
        beforReviewParamDto.setReviewId(befoReviewEntity.getReviewId());
        beforReviewParamDto.setAttachedPhotoIds(arrayListPhoto);
        beforReviewParamDto.setContent(befoReviewEntity.getContent());
        beforReviewParamDto.setFirstState(befoReviewEntity.getFirstState());
        int beforePoint = getReviewBouse(beforReviewParamDto);
        //리뷰DB 수정
        ReviewEntity reviewEntity = ReviewEntity.builder()
                    .reviewId(reviewParamDto.getReviewId())
                    .placeId(reviewParamDto.getPlaceId())
                    .userId(reviewParamDto.getUserId())
                    .attachedPhotoIds(""+reviewParamDto.getAttachedPhotoIds())
                    .content(reviewParamDto.getContent())
                    .firstState(befoReviewEntity.getFirstState())
                    .build();
        reviewMapper.updateReview(reviewEntity);
        //수정 된 리뷰의 지급예정 포인트 확인
        int point = getReviewBouse(reviewParamDto);
        //변경된 포인트 액수
        int pointValue = point - beforePoint;
        long finalPointValue = pointValue;
        //포인트 히스토리 추가
        PointHistoryEntity pointHistory = PointHistoryEntity.builder()
        .userId(reviewParamDto.getUserId())
        .pointValue(finalPointValue)
        .beforePoint(memberEntity.getPoint())
        .afterPoint(memberEntity.getPoint()+pointValue)
        .build();
        //차액 만큼 포인트 감소 및 추가
        if(pointValue != 0){
            pointHistoryService.createPointHistory(pointHistory);
            memberService.modifyMember(memberEntity, (long) pointValue);
        }
        
        return Long.valueOf(memberEntity.getPoint()).intValue();
    }

    @Validated(value = { ReadValidationGroup.class })
    @Transactional
    public Integer duplicateCheckReview(// 중복리뷰 체크용
            @Valid @NotNull(groups = { CreateValidationGroup.class }) String userId, String placeId) {
        return reviewMapper.selectDuplicateCheckReviewCount(userId, placeId);
    }

    @Validated(value = { ReadValidationGroup.class })
    @Transactional
    public Integer checkFirstReview( //해당 여행지의 첫리뷰인지 체크용
            @Valid @NotNull(groups = { CreateValidationGroup.class }) String placeId) {
        return reviewMapper.selectFristReviewCount(placeId);
    }

    @Validated (value = {RemoveValidationGroup.class})
    @Transactional
    public Integer removeReview( // 리뷰 삭제
            @Valid @NotNull (groups = {RemoveValidationGroup.class}) ReviewParamDto reviewParamDto,
            @Valid @NotNull (groups = {RemoveValidationGroup.class}) MemberEntity memberEntity) {
        //해당 리뷰에 부여된 포인트 확인
        int point = getReviewBouse(reviewParamDto) * -1;
        //리뷰 삭제
        reviewMapper.deleteReview(reviewParamDto.getReviewId());            
        //포인트 회수 히스토리 추가
        PointHistoryEntity pointHistory = PointHistoryEntity.builder()
        .userId(reviewParamDto.getUserId())
        .pointValue(Long.valueOf(point))
        .beforePoint(memberEntity.getPoint())
        .afterPoint(memberEntity.getPoint()+point)
        .build();
        pointHistoryService.createPointHistory(pointHistory);
        //포인트 회수
        memberService.modifyMember(memberEntity, (long) point);
        return memberEntity.getPoint().intValue();
    }

    @Validated (value = {ReadValidationGroup.class})
    @Transactional (readOnly = true)
    public Integer getReviewBouse( //지급 포인트 측정
            @Valid ReviewParamDto reviewParamDto) {
        int point = 0;
        if(reviewParamDto.getContent().length() > 0) {
            point++;
        }
        if(reviewParamDto.getAttachedPhotoIds().size() > 0) { //사진 첨부 체크
            point++;
        }
        if(reviewParamDto.getAction().equals("ADD")) {
            if(checkFirstReview(reviewParamDto.getPlaceId()) == 0){ //첫리뷰 여부 체크
                point++;
                reviewParamDto.setFirstState(1);
            }
        } else if(reviewParamDto.getAction().equals("DELETE") || reviewParamDto.getAction().equals("MOD")) {
            ReviewEntity reviewData = reviewMapper.selectReview(reviewParamDto.getReviewId());
            if(reviewData.getFirstState() == 1){ //첫리뷰 체크
                point++;
            }
        }
        return point;
    }

    @Validated(value = { ReadValidationGroup.class })
    @Transactional(readOnly = true)
    public List<ReviewEntity> getReviewList( // 여행지 리뷰 리스트
            @Valid ReviewParamDto reviewParamDto,
            PageRequest pageRequest) {
        List<ReviewEntity> reviewList = reviewMapper.selectReviewList(reviewParamDto, pageRequest);
        return reviewList;
    }

    @Validated(value = { ReadValidationGroup.class })
    @Transactional(readOnly = true)
    public ReviewEntity getReviewDetail( // 리뷰 상세 정보
            @Valid @NotNull(groups = { ReadValidationGroup.class }) Long index) {
        return reviewMapper.selectReview(index);
    }

}
