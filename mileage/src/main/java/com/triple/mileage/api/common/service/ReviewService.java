package com.triple.mileage.api.common.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.triple.mileage.api.common.entity.ReviewEntity;
import com.triple.mileage.api.common.mapper.ReviewMapper;
import com.triple.mileage.common.validation.group.CreateValidationGroup;

@Validated
@Service
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;
    
    @Validated(value = { CreateValidationGroup.class })
    @Transactional
    public Integer createReview(
            @Valid @NotNull(groups = { CreateValidationGroup.class }) ReviewEntity reviewEntity) {
                System.out.println("reviewEntity2222  :" + reviewEntity);
                Integer result = reviewMapper.insertReview(reviewEntity);
                // ReviewHistoryEntity reviewHistoryEntity = ReviewHistoryEntity.builder()
                // .reviewId(reviewEntity.getId())
                // .detail("글 작성"+"title :"+reviewEntity.getTitle()+" context :"+reviewEntity.getContext()+" userId :"+reviewEntity.getCreatedUserId())
                // .type((long) 1)
                // .build();
                // reviewHistoryMapper.insertReviewHistory(reviewHistoryEntity);
        return result;
    }

    @Validated(value = { CreateValidationGroup.class })
    @Transactional
    public Integer duplicateCheckReview(// 중복리뷰 체크용
            @Valid @NotNull(groups = { CreateValidationGroup.class }) String userId, String placeId) {
                System.out.println("placeId  :" + placeId);
                System.out.println("userId  :" + userId);
        return reviewMapper.selectDuplicateCheckReviewCount(userId, placeId);
    }

    @Validated(value = { CreateValidationGroup.class })
    @Transactional
    public Integer checkFirstReview( //해당 여행지의 첫리뷰인지 체크용
            @Valid @NotNull(groups = { CreateValidationGroup.class }) String placeId) {
                System.out.println("placeId  :" + placeId);
        return reviewMapper.selectFristReviewCount(placeId);
    }

}
