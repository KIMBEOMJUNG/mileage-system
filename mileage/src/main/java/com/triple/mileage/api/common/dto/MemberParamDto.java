package com.triple.mileage.api.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberParamDto {

    private Long id;

    private String userId;

    private String pw;

    private String nickname;

    private Long point;

}
