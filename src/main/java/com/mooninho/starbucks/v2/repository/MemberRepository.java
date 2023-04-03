package com.mooninho.starbucks.v2.repository;

import com.mooninho.starbucks.v2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
