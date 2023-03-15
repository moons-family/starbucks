package com.mooninho.starbucks.repository;

import com.mooninho.starbucks.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

//    public Optional<Member> findUseridByPhone(String phone) {
//        Member findMember = em.find(Member.class, phone);
//        return Optional.ofNullable(findMember);
//    }


}
