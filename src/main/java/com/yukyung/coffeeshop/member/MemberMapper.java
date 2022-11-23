package com.yukyung.coffeeshop.member;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    public Member memberPostDtoToMember(MemberPostDto memberPostDto);
    public Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    public MemberResponseDto memberToMemberResponseDto(Member member);
}
