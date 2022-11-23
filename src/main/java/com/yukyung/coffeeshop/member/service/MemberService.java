package com.yukyung.coffeeshop.member.service;

import com.yukyung.coffeeshop.member.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    public Member createMember(Member member) {
        return member;
    }

    public Member updateMember(Member member) {
        return member;
    }

    public Member findMember(long memberId) {
        return new Member(memberId,"aaa@gmail.com","차은우","010-1234-5678");
    }

    public List<Member> findMembers() {
        return List.of(
                new Member(1L, "aaa@gmail.com", "차은우", "010-1234-5678"),
                new Member(2L, "kdw@gmail.com", "강동원", "010-1111-2222")
        );
    }

    public void deleteMember(long memberId) {

    }
}
