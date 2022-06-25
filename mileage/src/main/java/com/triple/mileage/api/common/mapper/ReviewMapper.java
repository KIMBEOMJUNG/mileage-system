package com.triple.mileage.api.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.triple.mileage.api.common.dto.ReviewParamDto;
import com.triple.mileage.api.common.entity.ReviewEntity;
import com.triple.mileage.common.util.PageRequest;

@Repository
@Mapper
public interface ReviewMapper {

    public List<ReviewEntity> selectReviewList(
            @Param(value = "reviewParamDto") ReviewParamDto reviewParamDto,
            @Param(value = "pageRequest") PageRequest pageRequest);

    public Integer selectReviewListCount(
            @Param(value = "reviewParamDto") ReviewParamDto reviewParamDto);

    public ReviewEntity selectReview(
            @Param(value = "reviewId") Long reviewId);

    public Integer selectDuplicateCheckReviewCount(
        @Param(value = "userId") String userId, @Param(value = "placeId") String placeId);
        
    public Integer selectFristReviewCount(
            @Param(value = "placeId") String placeId);

    public ReviewEntity selectLoginReview(
            @Param(value = "reviewEntity") ReviewEntity reviewEntity);

    public Integer insertReview(
            @Param(value = "reviewEntity") ReviewEntity reviewEntity);

    public Integer updateReview(
            @Param(value = "reviewEntity") ReviewEntity reviewEntity);

    public Integer deleteReview(
            @Param(value = "reviewId") Long reviewId);
            
}
