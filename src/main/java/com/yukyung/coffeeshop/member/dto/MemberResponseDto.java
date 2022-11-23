package com.yukyung.coffeeshop.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponseDto { //응답 데이터 역할 해주는 DTO 클래스
    private long memberId;

    private String email;

    private String name;

    private String phone;
}
