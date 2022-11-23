package com.yukyung.coffeeshop.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private Long memberId;
    private String email;
    private String name;
    private String phone;
}
