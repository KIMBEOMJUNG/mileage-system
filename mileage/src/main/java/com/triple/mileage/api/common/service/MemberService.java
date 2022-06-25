package com.triple.mileage.api.common.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.triple.mileage.api.common.entity.MemberEntity;
import com.triple.mileage.api.common.mapper.MemberMapper;
import com.triple.mileage.common.validation.group.ModifyValidationGroup;
import com.triple.mileage.common.validation.group.ReadValidationGroup;

@Validated
@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;
    
    @Validated(value = { ReadValidationGroup.class })
    @Transactional(readOnly = true)
    public MemberEntity getMemberDetail(
        @Valid @NotNull(groups = { ReadValidationGroup.class }) Long userId) {
        
        return memberMapper.selectMember(userId);
    }

    @Validated (value = {ModifyValidationGroup.class})
    @Transactional
    public Integer modifyMember(
            @Valid @NotNull (groups = {ModifyValidationGroup.class}) MemberEntity memberEntity, @Valid @NotNull (groups = {ModifyValidationGroup.class}) Long point) {
                memberEntity.setPoint(memberEntity.getPoint()+point);
        return memberMapper.updateMember(memberEntity);
    }

}
