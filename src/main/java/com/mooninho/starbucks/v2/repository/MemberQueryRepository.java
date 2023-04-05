package com.mooninho.starbucks.v2.repository;

import com.mooninho.starbucks.v2.domain.Email;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.mooninho.starbucks.v2.entity.QMember.member;


@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public boolean isEmailExist(String email) {
        Integer result = queryFactory
                .selectOne()
                .from(member)
                .where(member.email.eq(Email.of(email)))
                .fetchFirst();

        return result != null && result == 1;
    }
}
