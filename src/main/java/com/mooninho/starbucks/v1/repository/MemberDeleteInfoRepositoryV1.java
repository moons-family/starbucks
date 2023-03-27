package com.mooninho.starbucks.v1.repository;

import com.mooninho.starbucks.v1.entity.DeleteMemberInfoV1;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDeleteInfoRepositoryV1 extends JpaRepository<DeleteMemberInfoV1, Long> {
}
