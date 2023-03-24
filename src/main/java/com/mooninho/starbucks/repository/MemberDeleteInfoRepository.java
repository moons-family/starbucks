package com.mooninho.starbucks.repository;

import com.mooninho.starbucks.entity.DeleteMemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDeleteInfoRepository extends JpaRepository<DeleteMemberInfo, Long> {
}
