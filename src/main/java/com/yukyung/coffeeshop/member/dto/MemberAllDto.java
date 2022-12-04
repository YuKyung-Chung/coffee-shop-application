package com.yukyung.coffeeshop.member.dto;

import com.yukyung.coffeeshop.member.entity.Member;
import com.yukyung.coffeeshop.member.entity.PageInfo;
import lombok.Getter;

@Getter
public class MemberAllDto<T> {
    private T data;
    private PageInfo pageInfo;

    public MemberAllDto(T data, PageInfo pageInfo) {
        this.data = data;
        this.pageInfo = pageInfo;
    }
}
