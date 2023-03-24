package com.mooninho.starbucks.repository;

import com.mooninho.starbucks.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}