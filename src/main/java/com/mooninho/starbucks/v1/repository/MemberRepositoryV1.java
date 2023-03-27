package com.mooninho.starbucks.v1.repository;

import com.mooninho.starbucks.v1.entity.MemberV1;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepositoryV1 extends JpaRepository<MemberV1, Long> {
}