package com.yukyung.coffeeshop.member;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    public Member createMember(Member member) {
        Member createdMember = member;
        return createdMember;
    }

    public Member updateMember(Member member) {
        Member updatedMember = member;
        return updatedMember;
    }

    public Member findMember(long memberId) {
        Member member = new Member(memberId,"aaa@gmail.com","차은우","010-1234-5678");
        return member;
    }

    public List<Member> findMembers() {
        List<Member> members = List.of(
                new Member(1L, "aaa@gmail.com", "차은우", "010-1234-5678"),
                new Member(2L, "kdw@gmail.com", "강동원", "010-1111-2222")
        );
        return members;
    }

    public void deleteMember(long memberId) {

    }
}
