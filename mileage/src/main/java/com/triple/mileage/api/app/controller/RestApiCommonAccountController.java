package com.triple.mileage.api.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.triple.mileage.api.common.dto.MemberDto;
import com.triple.mileage.api.common.entity.MemberEntity;
import com.triple.mileage.api.common.service.AccountService;

@RestController
public class RestApiCommonAccountController {
    
    @Autowired
    private AccountService accountService;

    //로그인 컨트롤러
    @PostMapping(path = "/account/login")
    public MemberEntity logining(
            HttpServletRequest request,
            @RequestBody MemberDto memberDto) {
            MemberEntity memberEntity = MemberEntity.builder()
                .userId(memberDto.getUserId())
                .pw(memberDto.getPw())
                .build();
        return accountService.getMember(request, memberEntity);
    }
   
}
