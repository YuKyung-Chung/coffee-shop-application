package com.yukyung.coffeeshop.member.repository;

import com.yukyung.coffeeshop.member.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member,Long> {
    //Spring Data JDBC에서 지원하는 쿼리 메서드 사용
    //결과 데이터를 Member 엔티티 클래스의 객체로 지정 -> 리턴값을 Optional로 래핑
    Optional<Member> findByEmail(String email);
}
