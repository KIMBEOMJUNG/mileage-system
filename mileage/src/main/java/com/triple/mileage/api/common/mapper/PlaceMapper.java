package com.triple.mileage.api.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.triple.mileage.api.common.dto.PlaceParamDto;
import com.triple.mileage.api.common.entity.PlaceEntity;
import com.triple.mileage.common.util.PageRequest;

@Repository
@Mapper
public interface PlaceMapper {

    public List<PlaceEntity> selectPlaceList(
            @Param(value = "placeParamDto") PlaceParamDto placeParamDto,
            @Param(value = "pageRequest") PageRequest pageRequest);

    public Integer selectPlaceListCount(
            @Param(value = "placeParamDto") PlaceParamDto placeParamDto);

    public PlaceEntity selectPlace(
            @Param(value = "id") Long id);

    public PlaceEntity selectLoginPlace(
            @Param(value = "placeEntity") PlaceEntity placeEntity);

    public Integer insertPlace(
            @Param(value = "placeEntity") PlaceEntity placeEntity);

    public Integer updatePlace(
            @Param(value = "placeEntity") PlaceEntity placeEntity);

    public Integer deletePlace(
            @Param(value = "id") Long id);
            
}
