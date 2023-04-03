package com.mooninho.starbucks.v2.repository;

import com.mooninho.starbucks.v2.domain.Email;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.mooninho.starbucks.v2.entity.QMember.member;


@Repository
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public MemberQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Boolean isEmailExist(String email) {
        Optional<Integer> result =
                Optional.ofNullable(queryFactory
                .selectOne()
                .from(member)
                .where(member.email.eq(Email.of(email)))
                .fetchFirst());
        return result.map(r -> r == 1).orElse(false);
    }
}
