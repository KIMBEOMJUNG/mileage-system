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

    @PostMapping(path = "/account/login")
    public MemberEntity logining(
            HttpServletRequest request,
            @RequestBody MemberDto memberDto) {
            MemberEntity memberEntity = MemberEntity.builder()
                .userId(memberDto.getUserId())
                .pw(memberDto.getPw())
                .build();
            System.out.println("memberEntity :" + memberEntity);
        return accountService.getMember(request, memberEntity);
    }

    // @GetMapping(path = "/account/login/{nickname}")
    // public MemberEntity getCreateLiked(
    //     HttpServletRequest request, @PathVariable(name = "nickname") String nickname) {
    //             MemberEntity member = accountService.getMember(nickname);
    //             if(member != null) {
    //                 HttpSession session = request.getSession();
    //                 session.setAttribute("id", member.getId());
    //                 session.setAttribute("nickname", member.getNickname());
    //                 session.setAttribute("accountId", member.getAccountId());
    //                 session.setAttribute("accountType", member.getAccountType());
    //                 session.setAttribute("accountTypeKor", member.getAccountTypeKor());
    //             }
    //     return member;
    // }

    // @GetMapping(path = "/account/checkType")
    // public int getAccountType(
    //     @RequestHeader("Authorization") String data) {
    //             String[] type = data.split("\\s+");
    //             if(type.length == 2) {
    //                 if(type[0] != null && type[0] != "") {
    //                     return 1;
    //                 }
    //             }
    //     return 0;
    // }
   
}
