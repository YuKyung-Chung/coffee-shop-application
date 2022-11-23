package com.yukyung.coffeeshop.member.mapper;

import com.yukyung.coffeeshop.member.dto.MemberResponseDto;
import com.yukyung.coffeeshop.member.dto.MemberPatchDto;
import com.yukyung.coffeeshop.member.dto.MemberPostDto;
import com.yukyung.coffeeshop.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    public Member memberPostDtoToMember(MemberPostDto memberPostDto);
    public Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    public MemberResponseDto memberToMemberResponseDto(Member member);
}
