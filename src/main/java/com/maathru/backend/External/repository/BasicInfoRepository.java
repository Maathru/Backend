package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.BasicInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicInfoRepository extends JpaRepository<BasicInfo, Long> {
}
