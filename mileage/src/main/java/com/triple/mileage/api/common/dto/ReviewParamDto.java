package com.triple.mileage.api.common.dto;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewParamDto {

    private Long reviewId;

    private String placeId;

    private String userId;

    private ArrayList<String> attachedPhotoIds;

    private String content;

    private String action;

}
