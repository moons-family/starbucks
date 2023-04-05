package com.mooninho.starbucks.v1.repository;

import com.mooninho.starbucks.v1.entity.MemberV1;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.mooninho.starbucks.v1.entity.QMemberV1.memberV1;

@Repository

public class MemberQueryRepositoryV1 {

    private final JPAQueryFactory queryFactory;

    public MemberQueryRepositoryV1(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public MemberV1 findMemberByEmail(String email) {

        return queryFactory
                .select(memberV1)
                .from(memberV1)
                .where(memberV1.email.eq(email))
                .fetchOne();
    }

    public void deleteMember(Long id) {

        queryFactory
                .delete(memberV1)
                .where(memberV1.id.eq(id))
                .execute();
    }
}
