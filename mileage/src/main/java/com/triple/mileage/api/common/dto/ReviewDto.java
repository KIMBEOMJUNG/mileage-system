package com.triple.mileage.api.common.dto;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private Long reviewId;

    private String placeId;

    private String userId;

    private ArrayList<String> attachedPhotoIds;

    private String content;

}
