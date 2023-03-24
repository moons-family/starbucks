package com.mooninho.starbucks.repository;

import com.mooninho.starbucks.dto.MemberLoginDTO;
import com.mooninho.starbucks.entity.Member;
import com.mooninho.starbucks.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.mooninho.starbucks.entity.QMember.*;

@Repository

public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public MemberQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Member findMemberByEmail(String email) {

        return queryFactory
                .select(new QMember(member))
                .from(member)
                .where(member.email.eq(email))
                .fetchOne();
    }
}
