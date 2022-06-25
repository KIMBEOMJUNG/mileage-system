package com.triple.mileage.api.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlaceParamDto {

    private Long id;

    private String placeName;

    private String placeContext;

}
