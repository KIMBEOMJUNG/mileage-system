package com.triple.mileage.api.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.triple.mileage.api.common.dto.PointHistoryParamDto;
import com.triple.mileage.api.common.entity.PointHistoryEntity;
import com.triple.mileage.common.util.PageRequest;

@Repository
@Mapper
public interface PointHistoryMapper {

    public List<PointHistoryEntity> selectPointHistoryList(
            @Param(value = "pointHistoryParamDto") PointHistoryParamDto pointHistoryParamDto,
            @Param(value = "pageRequest") PageRequest pageRequest);

    public Integer selectPointHistoryListCount(
            @Param(value = "pointHistoryParamDto") PointHistoryParamDto pointHistoryParamDto);

    public PointHistoryEntity selectPointHistory(
            @Param(value = "id") String id);

    public Integer insertPointHistory(
            @Param(value = "pointHistoryEntity") PointHistoryEntity pointHistoryEntity);

    public Integer deletePointHistory(
            @Param(value = "id") Long id);
            
}
