package com.yukyung.coffeeshop.basic;

import com.yukyung.coffeeshop.member.entity.Member;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class JpaBasicConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaBasicRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();

        return args -> {
            example01();
        };
    }

    private void example01() {
        Member member = new Member("happy@gmail.com");

        // 영속성 컨텍스트에 member 객체의 정보들이 저장
        // 실제 테이블에 정보가 저장되는 것은 아님
        em.persist(member);

        Member resultMember = em.find(Member.class, 1L); // find 메서드(조회할 엔티티 클래스 타입, 식별자 값)
        System.out.println("Id: " + resultMember.getMemberId() +
                ", email: " + resultMember.getEmail());
    }

}
