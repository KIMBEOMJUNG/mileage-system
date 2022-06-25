package com.triple.mileage.api.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.triple.mileage.api.common.dto.PlaceParamDto;
import com.triple.mileage.api.common.entity.PlaceEntity;
import com.triple.mileage.api.common.service.PlaceService;
import com.triple.mileage.common.util.PageRequest;
import com.triple.mileage.common.util.PageResponse;

@RestController
public class RestApiCommonPlaceController {
    
    @Autowired
    private PlaceService placeService;

    @GetMapping(path = "/place/get")
    public PageResponse<PlaceEntity> getPlaceList(
            @ModelAttribute PlaceParamDto placeParamDto,
            @ModelAttribute PageRequest pageRequest) {
        return placeService.getPlaceList(placeParamDto, pageRequest);
    }

}
