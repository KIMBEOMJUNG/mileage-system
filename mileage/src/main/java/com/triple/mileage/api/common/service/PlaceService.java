package com.triple.mileage.api.common.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.triple.mileage.api.common.dto.PlaceParamDto;
import com.triple.mileage.api.common.entity.PlaceEntity;
import com.triple.mileage.api.common.mapper.PlaceMapper;
import com.triple.mileage.common.util.PageRequest;
import com.triple.mileage.common.util.PageResponse;
import com.triple.mileage.common.validation.group.ReadValidationGroup;

@Validated
@Service
public class PlaceService {

    @Autowired
    private PlaceMapper placeMapper;
    
    @Validated(value = { ReadValidationGroup.class })
    @Transactional(readOnly = true)
    public PageResponse<PlaceEntity> getPlaceList(
            @Valid PlaceParamDto placeParamDto,
            PageRequest pageRequest) {
        Integer placeListCount = placeMapper.selectPlaceListCount(placeParamDto);
        List<PlaceEntity> placeList = placeMapper.selectPlaceList(placeParamDto, pageRequest);
        PageResponse<PlaceEntity> pageResponse = new PageResponse<>(pageRequest, placeListCount);
        pageResponse.setItems(placeList);
        return pageResponse;
    }

    @Validated(value = { ReadValidationGroup.class })
    @Transactional(readOnly = true)
    public PlaceEntity getPlaceDetail(
            @Valid @NotNull(groups = { ReadValidationGroup.class }) Long index) {
        return placeMapper.selectPlace(index);
    }

}
