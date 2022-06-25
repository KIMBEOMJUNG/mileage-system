package com.triple.mileage.api.common.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.triple.mileage.api.common.entity.MemberEntity;
import com.triple.mileage.api.common.mapper.MemberMapper;
import com.triple.mileage.common.validation.group.CreateValidationGroup;
import com.triple.mileage.common.validation.group.ReadValidationGroup;

@Validated
@Service
public class AccountService {

    @Autowired
    private MemberMapper memberMapper;

    @Validated(value = { ReadValidationGroup.class })
    @Transactional(readOnly = true)
    public MemberEntity getMember(
        HttpServletRequest request, @Valid @NotNull(groups = { CreateValidationGroup.class }) MemberEntity memberEntity) {
        MemberEntity result = memberMapper.selectLoginMember(memberEntity);
        if(result != null) {
            HttpSession session = request.getSession();
            session.setAttribute("id", result.getId());
            session.setAttribute("nickname", result.getNickname());
            session.setAttribute("point", result.getPoint());
        }
        return result;
    }

}
