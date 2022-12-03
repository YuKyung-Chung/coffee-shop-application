package com.yukyung.coffeeshop.member.controller;

import com.yukyung.coffeeshop.member.dto.MemberPatchDto;
import com.yukyung.coffeeshop.member.dto.MemberPostDto;
import com.yukyung.coffeeshop.member.dto.MemberResponseDto;
import com.yukyung.coffeeshop.member.entity.Member;
import com.yukyung.coffeeshop.member.mapper.MemberMapper;
import com.yukyung.coffeeshop.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v10/members")
@Validated
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;

    // MemberService, MemberMapper DI
    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberPostDto) {
        // Mapper를 이용해서 MemberPostDto를 Member로 변환
        Member member = mapper.memberPostDtoToMember(memberPostDto);

        Member response = memberService.createMember(member);

        // Mapper를 이용해서 Member를 MemberResponseDto로 변환
        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Min(1) long memberId,
                                      @Valid @RequestBody MemberPatchDto memberPatchDto) {
        memberPatchDto.setMemberId(memberId);

        Member response = memberService.updateMember(mapper.memberPatchDtoToMember(memberPatchDto));

        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response), HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId) {
        Member response = memberService.findMember(memberId);

        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() {
        List<Member> members = memberService.findMembers();

        // Mapper를 이용해서 List<Member>를 MemberResponseDto로 변환
        List<MemberResponseDto> response = members.stream()
                .map(member -> mapper.memberToMemberResponseDto(member))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Min(1) long memberId) {
        System.out.println("# memberId: " + memberId);

        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}