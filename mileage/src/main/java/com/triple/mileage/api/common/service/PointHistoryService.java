package com.triple.mileage.api.common.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.triple.mileage.api.common.entity.PointHistoryEntity;
import com.triple.mileage.api.common.mapper.PointHistoryMapper;
import com.triple.mileage.common.validation.group.CreateValidationGroup;

@Validated
@Service
public class PointHistoryService {

    @Autowired
    private PointHistoryMapper pointHistoryMapper;
    
    @Validated(value = { CreateValidationGroup.class })
    @Transactional
    public Integer createPointHistory(
            @Valid @NotNull(groups = { CreateValidationGroup.class }) PointHistoryEntity pointHistoryEntity) {
                System.out.println("pointHistoryEntity2222  :" + pointHistoryEntity);
        return pointHistoryMapper.insertPointHistory(pointHistoryEntity);
    }

}
